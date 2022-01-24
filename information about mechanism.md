*Swagger haqqinda
 - Swagger interfaceinnen apiler nece cagirilmalidir?

1.Postman vasitesile /oauth/token url'ne 
Authorization tabinda 'Basic auth' secilerek username ve password hisselerine 

 client id
 client id
 
muvafiq olaraq daxil edilir

2. eyni zamanda 'Body' tabinda  'x-www-url-form-encoded' secilerek

key-value hisselerine 
username - value
password-value
grant_type - password
daxil edilir.

1. POST  localhost:8057/oauth/token
   basic auth secerek  
   username =grps-default
   password = grp$!23

body
username =  QuerySelector   //QueryCreator//Admin
password = select      //create//Admin
grant_type = password  
token aliriq


3. POST methodu ile request edilib access token alinir.
daha sonra token goturulur ve swagger interfaceinde yerlesen sag uste 'Authorize' duymesinde yerlesen
hisseye 'value' hissesine "Bearer <token>" daxil edilir.
Acilan modal baglanir ve bunula interfacede yerlesen butun apilere avtomatik
token header hissede oturulmus olur.
