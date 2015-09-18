/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jopss.apostas.db.migration;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.DBRef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@ChangeLog(order = "002")
public class CadastroPerfil {

        @ChangeSet(order = "001", id = "002.001", author = "apostas")
        public void criarPerfil(DB db) {

                DBCollection perfil = db.getCollection("perfil");
                DBCollection permissao = db.getCollection("permissao");
                
                BasicDBObject filtro = new BasicDBObject();
                filtro.put("papel", new BasicDBObject("$in", Arrays.asList("ROLE_GERAL", "ROLE_ADMIN")));
                
                List listaPermissoes = new ArrayList();
                DBObject ob1 = permissao.findOne(new BasicDBObject("papel", "ROLE_ADMIN"));
                listaPermissoes.add(new DBRef("permissao", ob1.get("_id")));
                DBObject ob2 = permissao.findOne(new BasicDBObject("papel", "ROLE_GERAL"));
                listaPermissoes.add(new DBRef("permissao", ob2.get("_id")));
                

                BasicDBObject admin = new BasicDBObject();
                admin.append("dataatualizacao", null);
                admin.append("datacriacao", new Date());
                admin.append("nome", "Perfil Administrador");
                admin.append("descricao", "Perfil com permissão geral.");
                admin.append("permissoes", listaPermissoes);
                perfil.insert(admin);

                BasicDBObject visitante = new BasicDBObject();
                visitante.append("dataatualizacao", null);
                visitante.append("datacriacao", new Date());
                visitante.append("nome", "Perfil Visitante");
                visitante.append("descricao", "Perfil sem permissão para gerenciar uma aposta.");
                perfil.insert(visitante);
        }

}
