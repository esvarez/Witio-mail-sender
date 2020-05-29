var error = true

var res = [
    db.mail.drop(),
    db.mail.drop(),
    db.mail.insert({ myfield: 'hello', thatfield: 'testing' }),
]

printjson(res)

if (error) {
    print('Error, exiting')
    quit(1)
}
