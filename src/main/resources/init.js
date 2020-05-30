db.createUser({
    user: "esuarez",
    pwd: "esPass",
    roles: [{
        role: "readWrite",
        db: "mail"
    }]
})

printjson(res)

print('Error, exiting')
quit(1)
