/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        Pessoa professor = criarProfessor();
        
        EntityManager em = null;
        EntityTransaction et = null;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(aluno);
            em.persist(professor);
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
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2002);
        c.set(Calendar.MONTH, Calendar.MAY);
        c.set(Calendar.DAY_OF_MONTH, 11);
        aluno.setDataNascimento(c.getTime());
        aluno.addDisciplinas("Introdução a programação");
        aluno.addDisciplinas("Banco de Dados I");
        aluno.addDisciplinas("Algoritmos");
        criarContatoAluno(aluno);
        return aluno;
    }
    
    public static Professor criarProfessor() {
        Professor professor = new Professor();
        professor.setTitulo("Doutor");
        professor.setNome("Alvaro da Silva");
        professor.setEspecializacao("Doutorado em Ciencia da Computação");
        professor.setIdade(50);
        professor.setEstado("PE");
        professor.setCidade("Camaragibe");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1972);
        c.set(Calendar.MONTH, Calendar.SEPTEMBER);
        c.set(Calendar.DAY_OF_MONTH, 21);
        professor.setDataNascimento(c.getTime());
        professor.addDisciplinasEnsinadas("Banco de Dados I");
        professor.addDisciplinasEnsinadas("Banco de Dados II");
        criarContatoProfessor(professor);
        List<Turma> turmas = criarTurma(professor);
        professor.setTurmas(turmas);
        return professor;
    }
    
    public static void criarContatoAluno (Pessoa pessoa) {
        Contato contato = new Contato();
        contato.setEmail("juvenaldo1234@gmail.com");
        contato.setTelefone("81 9999-9999");
        contato.setCaixaPostal("70981");
        pessoa.setContato(contato);     
    }
    
    public static void criarContatoProfessor (Pessoa pessoa) {
        Contato contato = new Contato();
        contato.setEmail("dralvaro@gmail.com");
        contato.setTelefone("81 9999-8888");
        contato.setCaixaPostal("70001");
        pessoa.setContato(contato); 
    }
    
    public static List<Turma> criarTurma(Professor professor) {
        Turma turma1 = new Turma();
        turma1.setNomeTurma("Turma Banco de dados I");
        turma1.setCurso("TADS");
        turma1.setPeriodo("3 Periodo");
        turma1.setCapacidade(40);
        turma1.setProfessor(professor);
        
        Turma turma2 = new Turma();
        turma2.setNomeTurma("Turma Algoritmos");
        turma2.setCurso("TADS");
        turma2.setPeriodo("3 Periodo");
        turma2.setCapacidade(35);
        turma2.setProfessor(professor);
        
        List<Turma> turmas = new ArrayList<>();
        turmas.add(turma1);
        turmas.add(turma2);
        return turmas;
    }
}
