package kg.edu.iaau.hangman.entity;

import javax.persistence.*;

@Entity
@Table
public class SecretWord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public SecretWord(){}

    public SecretWord(String word)
    {
        this.word = word;
    }

    private String word;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    @Override
    public String toString()
    {
        return "SecretWord{" + "id=" + id + ", word='" + word + '\'' + '}';
    }
}
