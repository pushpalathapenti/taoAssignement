package com.tao.taskManager.utils;

import java.io.Serializable;
import java.util.Random;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomStringIdGenerator implements IdentifierGenerator {

    private static final int ID_LENGTH = 6;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        StringBuilder idBuilder = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            char randomChar = CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length()));
            idBuilder.append(randomChar);
        }
        return idBuilder.toString();
    }
}
