/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import dsc_projeto.projeto.tipos.TiposCurso;
import dsc_projeto.projeto.tipos.TiposTitulo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TesteJPA {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dsc_projeto");
    private static final Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        try {
            Integer id = inserirPessoa();
            consultarPessoa(id);
        } finally {
            emf.close();
        }
        
    }
    
    private static void consultarPessoa(Integer id) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            System.out.println("Consultando pessoa na base....");
            Pessoa pessoa = em.find(Pessoa.class, id);
            System.out.println("Imprimindo pessoa...");
            System.out.println(pessoa.toString());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public static Integer inserirPessoa() {
        Pessoa professor = criarProfessor();
        Disciplina disciplina = criarDisciplina((Professor) professor, ((Professor) professor).getCurso());
        Pessoa aluno = criarAluno(disciplina);
        
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
        
        return professor.getId();
    }
    
    
    
    public static Aluno criarAluno(Disciplina disciplina) {
        Aluno aluno = new Aluno();
        aluno.setNome("Juvenaldo");
        aluno.setEstado("PE");
        aluno.setCidade("Recife");
        aluno.setPeriodo("3 periodo");
        aluno.setMatricula("20222y3-9999");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 2002);
        c.set(Calendar.MONTH, Calendar.MAY);
        c.set(Calendar.DAY_OF_MONTH, 11);
        aluno.setDataNascimento(c.getTime());
        aluno.setDisciplinaRep(disciplina);
        aluno.setCurso(criarCursosAluno());
        criarContatoAluno(aluno);
        return aluno;
    }
    
    public static Professor criarProfessor() {
        Professor professor = new Professor();
        professor.setTitulo(TiposTitulo.DOUTOR);
        professor.setNome("Alvaro da Silva");
        professor.setEstado("PE");
        professor.setCidade("Camaragibe");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1972);
        c.set(Calendar.MONTH, Calendar.SEPTEMBER);
        c.set(Calendar.DAY_OF_MONTH, 21);
        professor.setDataNascimento(c.getTime());
        criarContatoProfessor(professor);
        Curso cursoProfessor = criarCursosProfessor();
        Disciplina disciplina = criarDisciplina(professor, cursoProfessor);
        Collection<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(disciplina);
        professor.setDisciplinas(disciplinas);
        professor.setCurso(cursoProfessor);
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
    
    public static Disciplina criarDisciplina(Professor professor, Curso curso) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNomeDisciplina("Turma Banco de dados I");
        disciplina.setPeriodo("3 Periodo");
        disciplina.setCapacidade(40);
        disciplina.setProfessor(professor);
        disciplina.setCurso(curso);
        return disciplina;
    }
    
    public static Curso criarCursosAluno() {
        Curso curso = new Curso();
        curso.setNomeCurso("Ciencia Computação");
        curso.setTipoCurso(TiposCurso.SUPERIOR);
        return curso;
    }
    
    public static Curso criarCursosProfessor() {
        Curso curso = new Curso();
        curso.setNomeCurso("Engenharia Computação");
        curso.setTipoCurso(TiposCurso.SUPERIOR);
        return curso;
    }
}
