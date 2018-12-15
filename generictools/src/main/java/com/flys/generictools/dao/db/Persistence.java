package com.flys.generictools.dao.db;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flys.generictools.tools.Utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persistence implements Serializable {

    private static final Persistence ourInstance = new Persistence();
    private static Context context;

    public static Persistence getInstance(Context context1) {

        context=context1;
        return ourInstance;
    }

    private Persistence() {
    }

    private List<Class<?>> entityClasses;

    public List<Class<?>> getEntityClasses() {
       Persistence persistence=ourInstance;
        ObjectMapper mapper = new ObjectMapper();
        String jsonInput = Utils.loadJSONFromAsset(context, "persistence.json");
        Log.e(getClass().getSimpleName(),"result:"+jsonInput);
        try {
            persistence = mapper.readValue(jsonInput, Persistence.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return persistence.getEntityClasses();
    }

    public void setEntityClasses(List<Class<?>> entityClasses) {
        this.entityClasses = entityClasses;
    }
}
