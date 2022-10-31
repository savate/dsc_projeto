/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class AlunoTeste extends Teste{
    @Test
    public void persistirAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno da Silva");
        aluno.setMatricula("2222x2-1111");
        aluno.setEstado("PE");
        aluno.setCurso("TADS");
        aluno.setCidade("Recife");
        aluno.setPeriodo("1 Periodo");
        Calendar c = Calendar.getInstance();
        c.set(2002, Calendar.SEPTEMBER, 24, 0, 0, 0);
        aluno.setDataNascimento(c.getTime());
        
        Contato contato = new Contato();
        contato.setEmail("alunodasilva@gmail.com");
        contato.setTelefone("81 9999-8877");
        contato.setCaixaPostal("41159");
        aluno.setContato(contato);
        
        Disciplina disciplina1 = em.find(Disciplina.class, 1);
        Disciplina disciplina2 = em.find(Disciplina.class, 2);
        Disciplina disciplina3 = em.find(Disciplina.class, 3);
        
        Collection<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(disciplina1);
        disciplinas.add(disciplina2);
        disciplinas.add(disciplina3);
        
        aluno.setDisciplinas(disciplinas);
        
        em.persist(aluno);
        em.flush();
        
        assertNotNull(aluno.getId());
    }
    
    @Test
    public void consultarAluno() {
        Aluno aluno = em.find(Aluno.class, 5);
        assertNotNull(aluno);
        assertEquals("Junior", aluno.getNome());
        assertEquals("2021.2", aluno.getMatricula());
        Calendar c = Calendar.getInstance();
        c.set(2000, Calendar.DECEMBER, 21, 0, 0, 0);
        assertEquals(c.getTime().toString(), aluno.getDataNascimento().toString());
        
        Contato contato = aluno.getContato();
        assertNotNull(contato);
        assertEquals("junior@email.com", contato.getEmail());
        assertEquals("1111-1111", contato.getTelefone());
        assertEquals("Recife01", contato.getCaixaPostal());
        
        Collection<Disciplina> disciplinas = aluno.getDisciplinas();
        assertEquals(1, disciplinas.size());
        assertEquals("Banco de Dados", disciplinas.iterator().next().getNomeDisciplina());
        assertEquals("Banco de Dados", aluno.getDisciplinaRep().getNomeDisciplina());
        
    }
}
