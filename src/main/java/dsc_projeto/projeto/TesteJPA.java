/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteJPA {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dsc_projeto");
    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            inserirPessoa();
        } finally {
            emf.close();
        }
        
    }
    
    public static Integer inserirPessoa() {
        Pessoa aluno = criarAluno();
        
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(aluno);
            et.commit();
        } catch (Exception ex){
            if (et != null && et.isActive()) {
                logger.log(Level.SEVERE,
                        "Cancelando transacao com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                logger.info("Transacao cancelada.");
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return aluno.getId();
    }
    
    public static Aluno criarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Carlitos");
        aluno.setIdade(20);
        aluno.setEstado("PE");
        aluno.setCidade("Recife");
        aluno.setCurso("TADS");
        aluno.setPeriodo("3 periodo");
        aluno.setMatricula("20222y3-9999");
        aluno.addDisciplinas("Introdução a programação");
        aluno.addDisciplinas("Banco de Dados I");
        aluno.addDisciplinas("Algoritmos");
        criarContato(aluno);
        return aluno;
    }
    
    public static void criarContato(Pessoa pessoa) {
        Contato contato = new Contato();
        contato.setEmail("juvenaldo1234@gmail.com");
        contato.setTelefone("81 9999-9999");
        contato.setCaixaPostal("70981");
        pessoa.setContato(contato);
    }
}
