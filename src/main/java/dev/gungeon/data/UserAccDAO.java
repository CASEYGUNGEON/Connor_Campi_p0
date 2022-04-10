package dev.gungeon.data;

import dev.gungeon.entities.UserAcc;

public interface UserAccDAO {

    UserAcc CreateUser(UserAcc user);

    UserAcc GetUser(int id);
}
