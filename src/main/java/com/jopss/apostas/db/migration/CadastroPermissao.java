package com.jopss.apostas.db.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import java.util.Date;

@ChangeLog(order = "001")
public class CadastroPermissao {

        @ChangeSet(order = "001", id = "001.001", author = "apostas")
        public void criarPermissoes(DB db) {
                
                DBCollection permissao = db.getCollection("permissao");
                
                BasicDBObject visitante = new BasicDBObject();
                visitante.append("dataatualizacao", null);
                visitante.append("datacriacao", new Date());
                visitante.append("nome", "Permissão de Visitante.");
                visitante.append("papel", "ROLE_VISITANTE");
                visitante.append("descricao", "Usuário sem permissão para gerenciar uma aposta.");
                visitante.append("permissaoPai", null);
                permissao.insert(visitante);
                
                BasicDBObject admin = new BasicDBObject();
                admin.append("dataatualizacao", null);
                admin.append("datacriacao", new Date());
                admin.append("nome", "Permissão de Administrador.");
                admin.append("papel", "ROLE_ADMIN");
                admin.append("descricao", "Permissão de Administrador.");
                admin.append("permissaoPai", null);
                permissao.insert(admin);
                
                BasicDBObject geral = new BasicDBObject();
                geral.append("dataatualizacao", null);
                geral.append("datacriacao", new Date());
                geral.append("nome", "Permissão Geral.");
                geral.append("papel", "ROLE_GERAL");
                geral.append("descricao", "Permissão geral da aplicação. Todos os perfis devem ter ao menos essa permissão.");
                geral.append("permissaoPai", null);
                permissao.insert(geral);
        }
        


//        @ChangeSet(order = "001", id = "initLoad", author = "apostas")
//        public void initPermissaoLoad(DB db) {
//                DBCollection permissao = db.getCollection("permissao");
//                BasicDBObject doc = new BasicDBObject().append("nome", "Kelsen KKBrito Silva Kleber Souza Filho");
//                usuario.insert(doc);
//        }

}
