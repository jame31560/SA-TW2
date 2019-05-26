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

Pelease set up phpmyadmin and import the sample data in sql folder.
And please fill the setting in DBMgr.java Line 16.
```
conn = DriverManager.getConnection("jdbc:mysql://"
    + "{phpmyadmin server ip}:{port}/{database name}?"
    + "user={username}&"
    + "password={password}&"
    + "useUnicode=true");
```