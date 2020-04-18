db.createUser(
    {
        user: "myUserAdmin",
        pwd: "87654321",
        roles: [{role: "userAdmin", db: "admin"}]
    })