# SA-TW2

## Compiles
```
javac -encoding utf-8 -d ./bin ./Main.java
```

## Run

### Go to bin
```
cd ./bin
```

### Run the project
```
java Main
```

## Set up database

Pelease set up phpmyadmin and import the sample data which in sql folder.  
Then fill the following settings in DBMgr.java Line 16.  
* phpmyadmin server ip
* port
* database name
* username of phpmyadmin
* password of phpmyadmin

The filling format like following.
```
conn = DriverManager.getConnection("jdbc:mysql://"
    + "{phpmyadmin server ip}:{port}/{database name}?"
    + "user={username}&"
    + "password={password}&"
    + "useUnicode=true");
```
