/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsc_projeto.projeto;

import dsc_projeto.projeto.tipos.TiposTitulo;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class ProfessorTeste extends Teste{
    @Test
    public void persistirProfessor() {
        Professor professor = new Professor();
        professor.setNome("Professor dos Santos");
        professor.setTitulo(TiposTitulo.DOUTOR);
        professor.setEstado("PE");
        professor.setCidade("Jaboatão");
        Calendar c = Calendar.getInstance();
        c.set(1991, Calendar.OCTOBER, 12, 0, 0, 0);
        professor.setDataNascimento(c.getTime());
        
        Contato contato = new Contato();
        contato.setEmail("professordossantos@gmail.com");
        contato.setTelefone("81 7777-8887");
        contato.setCaixaPostal("Jaboatao01");
        professor.setContato(contato);
        
        Disciplina disciplina = em.find(Disciplina.class, 2);
        Collection<Disciplina> disciplinas = new HashSet<>();
        disciplinas.add(disciplina);  
        professor.setDisciplinas(disciplinas);
        
        Curso curso = em.find(Curso.class, 1);
        professor.setCurso(curso);
        
        Collection<String> especializacoes = new HashSet<>();
        especializacoes.add("BlockChain e Criptografia Digital");
        especializacoes.add("Especialização de criptografia aplicada");
        professor.setEspecializacoes(especializacoes);
        
        em.persist(professor);
        em.flush();
        
        assertNotNull(professor.getId());
        assertNotNull(disciplina.getId());
    }
    
    @Test
    public void consultarProfessor() {
        Professor professor = em.find(Professor.class, 2);
        assertNotNull(professor);
        assertEquals("Maria", professor.getNome());
        assertEquals("PE", professor.getEstado());
        assertEquals("Olinda", professor.getCidade());
        assertEquals(TiposTitulo.MESTRE, professor.getTitulo());
        assertEquals("Programação Web Google", professor.getEspecializacoes().iterator().next());
        Calendar c = Calendar.getInstance();
        c.set(1995, Calendar.SEPTEMBER, 01, 0, 0, 0);
        assertEquals(c.getTime().toString(), professor.getDataNascimento().toString());
        
        Contato contato = professor.getContato();
        assertNotNull(contato);
        assertEquals("maria@email.com", contato.getEmail());
        assertEquals("8888-8888", contato.getTelefone());
        assertEquals("Olinda01", contato.getCaixaPostal());
        
        Curso curso = professor.getCurso();
        assertEquals("TADS", curso.getNomeCurso());
       
        Collection<Disciplina> disciplinas = professor.getDisciplinas();
        assertEquals(1, disciplinas.size());
        assertEquals("Web 1", disciplinas.iterator().next().getNomeDisciplina());
        assertEquals(2, disciplinas.iterator().next().getAlunos().size());
        
    }
}
