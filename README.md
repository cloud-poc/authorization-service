# authorization-service  

# Token generation  
__1. Authorization code:__  

* get auth-code  
request from browser:   
http://localhost:8080/uaa/oauth/authorize?client_id=client_3&redirect_uri=http://localhost:8080/callback&response_type=code&scope=read  
response:  
http://localhost:8080/callback?code=8uYpdo  

* obtain access token using code generated in #1  
curl -X POST --user client_3:123456 http://localhost:8080/uaa/oauth/token -H "content-type: application/x-www-form-urlencoded" -d "code=8uYpdo&grant_type=authorization_code&redirect_uri=http%3A%2F%2Flocalh ost%3A8080%2Fcallback&scope=read"

response sample：  


    {
        "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqYW1pZSIsInNjb3BlIjpbImFsbCIsInJlYWQiLCJ3cml0ZSJdLCJvcmdhbml6YXRpb24iOiJha2oub3JnIiwiZXhwIjoxNTUzMDc4NjQ3LCJhdXRob3JpdGllcyI6WyIwMjEwOTgiLCIwMjEwNDAiLCJST0xFX0FETUlOIl0sImp0aSI6IjFkYjdhNjg1LWJiM2QtNDJmMy1iNmEyLTAwMzBkZGFkZDAxZCIsImNsaWVudF9pZCI6ImNsaWVudF8yIn0.OAd9VpJoTTeAmefjfKXOSYu3iKtyyH-QQuSHxD7a-qlRubO0byTYl6MPrIGv6ZTc_CtWJAoAaBc3alM_NR1dMmiZs6YCM9syHuERtZxyS9HNxHSb8-4sm_grszb_U9Up7_q5AqkAl3KT-CTnip0Hshyilc_XdV7dXlCO8lQPfWJ9Yp30bIlCKVgrm48B9slU5jPn27SYp-orPYS5NYNdaIayD4k2Pmu-8cQxqyqOnlRh7Y-oscpXRdhGejQo2-LZot5nU-FYbPGQGc9VzZoStvaTKtFtOu13G20fhtKHKs7RGc0SzV2ciahxu06EcGRXu7vgPCWo4RImSs4FVRPmAQ",
        "token_type": "bearer",
        "refresh_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqYW1pZSIsInNjb3BlIjpbImFsbCIsInJlYWQiLCJ3cml0ZSJdLCJvcmdhbml6YXRpb24iOiJha2oub3JnIiwiYXRpIjoiMWRiN2E2ODUtYmIzZC00MmYzLWI2YTItMDAzMGRkYWRkMDFkIiwiZXhwIjoxNTUzMDgxNDQ3LCJhdXRob3JpdGllcyI6WyIwMjEwOTgiLCIwMjEwNDAiLCJST0xFX0FETUlOIl0sImp0aSI6IjRkMWFjYTE2LTZhNDUtNDM5Ny1hOGRiLTAzZTY4NjVjMDEyMyIsImNsaWVudF9pZCI6ImNsaWVudF8yIn0.Nfo4cRPupaiivKJTwTg_6SEl8p4v5a10Dhw0puUHgvoX4JU0yXKGoI32JoVWzTBx83Y6YaHnnPnOptBW-RC_HdwliFJ5j37E-BQNZk6HL30eqQyRh_Iipu9YCZd4xxwKEj7hQd3LgE8ePNFDRC_gbojF6hy86IQXHfgjyzN8QPVULxvYQmz9-slKZ-F1L4fU5YORjQnzd5Ji3t9xGw67jYO9HN6reRxz97NntgxwOOvWi0VZvXiVPGc0TCl_UDjLAXSOjfRUzzUostw001zD6y3H0eOg5Nqxu_3Xspbp19kifPk_vMeuOTeeqEgKep1caV421lTnVqdJiDOAOYYhaw",
        "expires_in": 7199,
        "scope": "all read write",
        "organization": "akj.org",
        "jti": "1db7a685-bb3d-42f3-b6a2-0030ddadd01d"
    }
 
__1. Password__  

curl -X POST --user client_2:123456 http://localhost:8080/uaa/oauth/token -H "accept: application/json" -H "content-type: application/x-www-form-urlencoded" -d "grant_type=password&username=jamie&password=123456&scope=read"  
  
__3. Client/Server__  

curl -X POST "http://localhost:8080/uaa/oauth/token" --user client_1:123456 -d "grant_type=client_credentials&scope=read"  
  
__4. Implicit__ 

http://localhost:8080/uaa/oauth/authorize?client_id=client_2&redirect_uri=http://localhost:8080/callback&response_type=token&scope=read&state=abc  
response sample:  


    http://localhost:8080/callback#access_token=eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqYW1pZSIsInNjb3BlIjpbInJlYWQiXSwib3JnYW5pemF0aW9uIjoiYWtqLm9yZyIsImV4cCI6MTU1MzA4Mjc4NiwiYXV0aG9yaXRpZXMiOlsiMDIxMDk4IiwiMDIxMDQwIiwiUk9MRV9BRE1JTiJdLCJqdGkiOiIwNTNiZDIwMC02ZmM0LTRhYmItYmQ3MC1hNjQyMjNiZWFlN2IiLCJjbGllbnRfaWQiOiJjbGllbnRfMiJ9.Vc2TXHs78PweRnntcfY-KfcXLHD1OdLELMAbDazlfA-aPZuwamSEHQEl7KluFzUjRkbNUV4HAExEELVygXqgAQ8kUmLzKRQ-IdoPh7TZCkRaMIzERIzn9TVKWRaci5wij6Xyr-fPKHMsXyAqAvUux2dYyXaW993l4mdGmMmp6Ub2WF7BIN85EP1iN5MSGQfPwKOk5hUbgku32p8pymBZg5-Y5_1yLgC2WVC9PQgY4zZG7z8v7YMsyl3ueJjeuD08CKV6hX2rAcRrtlrTZBL-BCnOW5xmsk9xWrPNYCOu4mLQWcH3X7rWVDwH9c9ftdtw-8DKCS2brZ5yJeTgmucEgA&token_type=bearer&state=abc&expires_in=7199&organization=akj.org&jti=053bd200-6fc4-4abb-bd70-a64223beae7b  

# Client registration - Client_id/Client_secret__  


     curl -X POST \
       http://localhost:8080/uaa/profile/clients \
       -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqYW1pZSIsInNjb3BlIjpbImFsbCIsInJlYWQiLCJ3cml0ZSJdLCJvcmdhbml6YXRpb24iOiJha2oub3JnIiwiZXhwIjoxNTUzMDczNjEyLCJhdXRob3JpdGllcyI6WyIwMjEwOTgiLCIwMjEwNDAiLCJST0xFX0FETUlOIl0sImp0aSI6ImY3OTQxNjYzLWIyMTEtNDFlYi05ODFjLTJlZGU1MmRjYTU0ZSIsImNsaWVudF9pZCI6ImNsaWVudF8yIn0.XH63RDlwtFIREW1Ka3waTpIIMJD4IgLpgdq2HuVFwEC3UtZGieQG1ZitGKJnQpmZRCX1ncanYr2YH5k-rTeVj50hv2K0xYTjc9VdgcypDOKIopnKyfnTTgaoqYm0GVRBK6YtW9ArcwIjrBW_94Z-Nm44SpAVoOBsN4Ze6bGkYN2KfYnjRFQYLJzIFm2n0Fb82OmVJxZ9PDWIdE4Xqy3Tu1K9Ghs8G-ulNufkTA61QpVPW-zVy9KFfyyJfDKnE4CIEJJfih00fm7ZocVFmOvmP002YMqkW6LzrKYQC2YuV1IXmgxZluaYg5sBOBQq3eGRzmGvO1bNl5iQO3h-jh_oOw' \
       -H 'Cache-Control: no-cache' \
       -H 'Content-Type: application/json' \
       -H 'Postman-Token: 275cdef3-02c1-10d9-ff5b-8274ac73b3b8' \
       -H 'client_id: client_2' \
       -H 'client_secret: 123456' \
       -d '{
     	"authorized_grant_types": ["password"],
     	"scope": "read",
     	"access_token_validity": 600,
     	"refresh_token_validity": 3600
     }'
    
  response sample:   
  
  
      {
        "scope": [
            "read"
        ],
        "client_id": "e6e3b13576444d5fbd7785660b91a394",
        "client_secret": "6VLWcz7JBTPZMY4CRW9JQkaQ9XBbS92j",
        "authorized_grant_types": [
            "password"
        ],
        "access_token_validity": 600,
        "refresh_token_validity": 3600
    }
  
     


