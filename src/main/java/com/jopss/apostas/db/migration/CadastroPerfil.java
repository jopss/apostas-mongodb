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
import java.util.Arrays;
import java.util.Date;

@ChangeLog(order = "002")
public class CadastroPerfil {

        @ChangeSet(order = "001", id = "002.001", author = "apostas")
        public void criarPerfil(DB db) {

                DBCollection perfil = db.getCollection("perfil");
                DBCollection permissao = db.getCollection("permissao");
                
                BasicDBObject filtro = new BasicDBObject();
                filtro.put("papel", new BasicDBObject("$in", Arrays.asList("ROLE_GERAL", "ROLE_ADMIN")));

                BasicDBObject admin = new BasicDBObject();
                admin.append("dataatualizacao", null);
                admin.append("datacriacao", new Date());
                admin.append("nome", "Perfil Administrador");
                admin.append("descricao", "Perfil com permissão geral.");
                admin.append("permissoes", permissao.find(filtro, new BasicDBObject("_id", 1)));
                perfil.insert(admin);

                BasicDBObject visitante = new BasicDBObject();
                visitante.append("dataatualizacao", null);
                visitante.append("datacriacao", new Date());
                visitante.append("nome", "Perfil Visitante");
                visitante.append("descricao", "Perfil sem permissão para gerenciar uma aposta.");
                perfil.insert(visitante);
        }

}
