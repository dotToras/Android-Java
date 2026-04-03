package com.example.quizads;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlActivity extends AppCompatActivity {



    LinearLayout containerQuiz;
    Button btEnviar;
    List<Questao> listaQuestoes = new ArrayList<>();
    int numb;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sql);


        if( Global.prova == 1 ) {
            preencherQuestoesSO();
        }
        else if ( Global.prova == 2 ) {
            preencherQuestoesJava();
        }
        else if ( Global.prova == 3 ) {
            preencherQuestoesSQL();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btEnviar = findViewById(R.id.btEnviar);
        containerQuiz = findViewById(R.id.layout01);

        TextView tv = new TextView(this);
        // para as questões
        for(int i = 0; i < listaQuestoes.size(); i++) {

            Questao q = listaQuestoes.get(i);

            TextView tvPergunta = new TextView(this);
            tvPergunta.setText(i + 1 + "-" + q.getQuestao());
            tvPergunta.setTextSize(18);

            RadioGroup rg = new RadioGroup(this);
            rg.addView(tvPergunta);
            rg.setOrientation(RadioGroup.VERTICAL);

            // para as opções
            for (int j = 0; j < 4; j++) {
                
                RadioButton rbOpcoes = new RadioButton(this);
                rbOpcoes.setText(q.getPerguntas().get(j));
                rbOpcoes.setId(View.generateViewId());

                rg.addView(rbOpcoes);
            }
            containerQuiz.addView(rg);

        }

        btEnviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int acertos = 0;
                int totalQuestoes = listaQuestoes.size();

                // Percorre o container para verificar as respostas
                for (int i = 0; i < containerQuiz.getChildCount(); i++) {

                    // verifica se é um radio grou
                    if (containerQuiz.getChildAt(i) instanceof RadioGroup) {
                        
                        RadioGroup rg = (RadioGroup) containerQuiz.getChildAt(i);
                        int idSelecionado = rg.getCheckedRadioButtonId();

                        // verifica se tem um radio button selecionado
                        if (idSelecionado != -1) {
                            RadioButton rb = rg.findViewById(idSelecionado);
                            String respostaUsuario = rb.getText().toString();
                            String respostaCerta = listaQuestoes.get(i).getRespostaCerta();

                            if (respostaUsuario.equals(respostaCerta)) {
                                acertos++;
                            }
                        }
                    }
                }

                Toast.makeText(SqlActivity.this, "Você acertou " + acertos + " de " + totalQuestoes, Toast.LENGTH_LONG).show();
                /* 
                Intent intent = new Intent(SqlActivity.this, ResultadoActivity.class);
                intent.putExtra("ACERTOS", acertos);
                intent.putExtra("TOTAL", totalQuestoes);
                startActivity(intent);
                finish(); 
                */
            }
            
        });
    
}


    public void preencherQuestoesSQL() {

        listaQuestoes.add(new Questao("Qual comando é utilizado para apagar todos os registros de uma tabela sem remover a estrutura da tabela?", Arrays.asList("DELETE", "TRUNCATE", "DROP", "REMOVE"), "TRUNCATE"));
        listaQuestoes.add(new Questao("Qual cláusula é usada para filtrar resultados de uma função de agregação (como SUM ou COUNT)?", Arrays.asList("WHERE", "ORDER BY", "HAVING", "GROUP BY"), "HAVING"));
        listaQuestoes.add(new Questao("O que significa a sigla ACID em bancos de dados relacionais?", Arrays.asList("Atomicidade, Consistência, Isolamento e Durabilidade", "Acesso, Controle, Identificação e Dados", "Agilidade, Compartilhamento, Integração e Desempenho", "Armazenamento, Conexão, Indexação e Distribuição"), "Atomicidade, Consistência, Isolamento e Durabilidade"));
        listaQuestoes.add(new Questao("Qual tipo de JOIN retorna apenas as linhas que possuem correspondência em ambas as tabelas?", Arrays.asList("LEFT JOIN", "RIGHT JOIN", "FULL JOIN", "INNER JOIN"), "INNER JOIN"));
        listaQuestoes.add(new Questao("Para que serve a restrição UNIQUE em uma coluna?", Arrays.asList("Garante que a coluna seja a chave primária", "Impede valores duplicados na coluna", "Impede valores nulos", "Faz a ordenação automática"), "Impede valores duplicados na coluna"));
        listaQuestoes.add(new Questao("Qual comando SQL é usado para modificar dados existentes em uma tabela?", Arrays.asList("MODIFY", "CHANGE", "UPDATE", "SET"), "UPDATE"));
        listaQuestoes.add(new Questao("Qual função é usada para contar o número de linhas em um conjunto de resultados?", Arrays.asList("SUM()", "TOTAL()", "COUNT()", "NUMBER()"), "COUNT()"));
        listaQuestoes.add(new Questao("Como você seleciona todos os registros de uma tabela chamada 'Clientes' onde o nome começa com 'A'?", Arrays.asList("SELECT * FROM Clientes WHERE Nome = 'A%'", "SELECT * FROM Clientes WHERE Nome LIKE 'A%'", "SELECT * FROM Clientes WHERE Nome START 'A'", "SELECT * FROM Clientes WHERE Nome CONTAINS 'A'"), "SELECT * FROM Clientes WHERE Nome LIKE 'A%'"));
        listaQuestoes.add(new Questao("Qual objeto é usado para melhorar a velocidade de recuperação de dados em uma tabela?", Arrays.asList("Trigger", "View", "Index", "Procedure"), "Index"));
        listaQuestoes.add(new Questao("Qual o objetivo do comando ROLLBACK?", Arrays.asList("Salvar alterações permanentemente", "Desfazer alterações da transação atual", "Reiniciar o servidor de banco de dados", "Excluir um backup"), "Desfazer alterações da transação atual"));

    }

    public void preencherQuestoesJava() {

        listaQuestoes.add(new Questao("Qual é a superclasse de todas as classes em Java?", Arrays.asList("String", "Main", "Object", "Class"), "Object"));
        listaQuestoes.add(new Questao("Qual palavra-chave é usada para impedir que uma classe seja herdada?", Arrays.asList("static", "final", "private", "abstract"), "final"));
        listaQuestoes.add(new Questao("Como se chama o processo de ter múltiplos métodos com o mesmo nome, mas parâmetros diferentes?", Arrays.asList("Sobrescrita (Override)", "Encapsulamento", "Polimorfismo", "Sobrecarga (Overload)"), "Sobrecarga (Overload)"));
        listaQuestoes.add(new Questao("Qual o valor padrão de uma variável booleana de instância em Java?", Arrays.asList("true", "false", "null", "0"), "false"));
        listaQuestoes.add(new Questao("Qual interface deve ser implementada para permitir que uma classe seja usada em uma Thread?", Arrays.asList("Runnable", "Threadable", "Executable", "Startable"), "Runnable"));
        listaQuestoes.add(new Questao("Qual modificador de acesso permite visibilidade apenas dentro da mesma classe?", Arrays.asList("public", "protected", "default", "private"), "private"));
        listaQuestoes.add(new Questao("Qual estrutura de dados em Java não permite elementos duplicados?", Arrays.asList("ArrayList", "LinkedList", "HashSet", "Vector"), "HashSet"));
        listaQuestoes.add(new Questao("O que o Java Virtual Machine (JVM) faz?", Arrays.asList("Compila o código .java", "Executa o bytecode Java", "Gerencia o hardware do computador", "Cria o instalador do programa"), "Executa o bytecode Java"));
        listaQuestoes.add(new Questao("Qual palavra-chave é usada para invocar o construtor da classe pai?", Arrays.asList("this", "parent", "super", "extends"), "super"));
        listaQuestoes.add(new Questao("Qual bloco é usado para garantir a execução de um código, independente de ocorrer uma exceção?", Arrays.asList("catch", "try", "finally", "throw"), "finally"));

    }

    public void preencherQuestoesSO() {

        listaQuestoes.add(new Questao("O que é o Kernel de um sistema operacional?", Arrays.asList("A interface gráfica", "O núcleo que gerencia os recursos de hardware", "O sistema de arquivos", "O editor de registro"), "O núcleo que gerencia os recursos de hardware"));
        listaQuestoes.add(new Questao("Qual o nome da situação onde dois processos esperam um pelo outro indefinidamente?", Arrays.asList("Starvation", "Context Switch", "Deadlock", "Paging"), "Deadlock"));
        listaQuestoes.add(new Questao("O que é memória virtual?", Arrays.asList("Memória RAM física adicional", "Uma técnica que usa o disco rígido como extensão da RAM", "O cache do processador", "Um drive de armazenamento na nuvem"), "Uma técnica que usa o disco rígido como extensão da RAM"));
        listaQuestoes.add(new Questao("Qual algoritmo de escalonamento de processos dá a cada processo um tempo fixo de CPU (quantum)?", Arrays.asList("FIFO", "SJF (Shortest Job First)", "Priority", "Round Robin"), "Round Robin"));
        listaQuestoes.add(new Questao("O que é uma System Call (Chamada de Sistema)?", Arrays.asList("Um erro no sistema", "Interface entre um programa e o kernel", "Uma atualização de software", "Um comando de terminal"), "Interface entre um programa e o kernel"));
        listaQuestoes.add(new Questao("Qual a principal função do Gerenciador de Dispositivos (Device Drivers)?", Arrays.asList("Formatar o disco", "Permitir que o SO se comunique com o hardware", "Aumentar a velocidade da internet", "Criar partições de disco"), "Permitir que o SO se comunique com o hardware"));
        listaQuestoes.add(new Questao("O que significa 'Preempção' em escalonamento?", Arrays.asList("A capacidade de interromper um processo em execução", "O fim natural de um processo", "A alocação de memória permanente", "O carregamento do sistema"), "A capacidade de interromper um processo em execução"));
        listaQuestoes.add(new Questao("Qual o papel do Spooling?", Arrays.asList("Acelerar o processador", "Gerenciar o acesso a dispositivos periféricos como impressoras", "Criptografar arquivos", "Limpar a memória cache"), "Gerenciar o acesso a dispositivos periféricos como impressoras"));
        listaQuestoes.add(new Questao("O que é um Processo em um sistema operacional?", Arrays.asList("Um arquivo salvo no disco", "Um programa em execução", "Um comando de rede", "Uma peça de hardware"), "Um programa em execução"));
        listaQuestoes.add(new Questao("O que é fragmentação externa na gerência de memória?", Arrays.asList("Quando há espaço total livre, mas não contíguo", "Quando um arquivo é corrompido", "Quando o disco rígido para de funcionar", "Quando a CPU aquece demais"), "Quando há espaço total livre, mas não contíguo"));

    }
}

