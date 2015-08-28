package com.jopss.apostas.db.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog(order = "001")
public class CadastroInicial {

        @ChangeSet(order = "001", id = "initLoad", author = "apostas")
        public void initLoad(DB db) {
                DBCollection usuario = db.getCollection("usuario");
                BasicDBObject doc = new BasicDBObject().append("nome", "Kelsen KKBrito Silva Kleber Souza Filho");
                usuario.insert(doc);
        }

}
