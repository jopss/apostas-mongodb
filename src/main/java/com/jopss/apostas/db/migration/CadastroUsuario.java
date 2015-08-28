package com.jopss.apostas.db.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.Date;

@ChangeLog(order = "003")
public class CadastroUsuario {
        
        @ChangeSet(order = "001", id = "003.001", author = "apostas")
        public void criarUsuario(DB db) {
                
                DBCollection usuario = db.getCollection("usuario");
                DBCollection perfil = db.getCollection("perfil");
                
                BasicDBObject admin = new BasicDBObject();
                admin.append("dataatualizacao", null);
                admin.append("datacriacao", new Date());
                admin.append("nome", "Administrador");
                admin.append("login", "admin");
                admin.append("senha", "21232f297a57a5a743894a0e4a801fc3");
                admin.append("perfil", perfil.findOne(new BasicDBObject("nome", "Perfil Administrador"), new BasicDBObject("_id", 1)));
                usuario.insert(admin);
        }
        
}
