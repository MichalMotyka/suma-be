Create table Users(
                      id varchar(36) PRIMARY KEY,
                      login varchar(32) not null,
                      password varchar(128) not null,
                      permission varchar(20) not null,
                      currentToken varchar(184)
);