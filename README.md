## Bot que automatiza conexões do Linkedin

*Projeto feito somente para praticar conceitos e tecnologias sem outros fins* 

### Bot em funcionamento
<br>

<img src="/readmeImgs/botEmFuncionamento.gif" alt="Bot em funcionamento" width="525" height="360" />

<br><br>

### Limite de conexões
No momento em que escrevo essa descrição, atingi meu limite semanal de conexões no Linkedin (como pode ser notado no gif acima). 

Entretanto o bot não continua o funcionamento nessa ocasião, alterei o código rapidamente apenas para demonstrar como seria seu funcionamento em condições normais.

Quando o limite for antigido, o bot lançará uma exceção parando o programa. Veja:

<br>

<img src="/readmeImgs/botEmFuncionamentoComLimiteDeConexoes.gif" alt="Bot em funcionamento com limite de conexoes" width="525" height="360" />

<br><br>

## Introdução
O bot automatiza o processo de enviar solicitações de conexão para profissionais selecionados por você previamente.

### Tencologias Usadas
- Java 17
- Selenium 4.7.2
- Chrome Driver
- Maven

### Padrão Usado
- POM (Page Object Model)

<br>

## Configurações

### Driver
Estou usando o chrome para as automações. Para ser possível utilizá-lo, devemos ter um driver da mesma versão do browser que temos instalado no nosso sistema.

Para isso, vá em [chrome driver dowloads](https://chromedriver.chromium.org/downloads).

Salve em um diretório que tenha conhecimento futuramente e copie o caminho do mesmo e adicione em [*config.properties*](/src/main/resources/config.properties).

`
driver.path=caminhoDoDriver
`

<br>

### Login

No código, automatizei o processo de login. Para isso você precisará colocar suas informações em [*config.properties*](/src/main/resources/config.properties).

`
linkedin.login=suaInformaçaoLogin
`

`
linkedin.password=suaSenha
`

<br>

#### **Recomendação**
Caso você vá utilizar a automação diversas vezes, para evitar possíveis punições antibot do Linkedin, recomendo usar um profile do browser que já esteja conectado na sua conta. 

Para isso, você precisará acessar no seu Chrome um perfil que você saiba o diretório em sua máquina, e logar no seu linkedin com o mesmo, com isso você continuará logado após fechá-lo. Após isso, adicionar o caminho desse diretório em [*config.properties*](/src/main/resources/config.properties).


`
driver.path.profile=profileDoSeuBrowserComOLoginJaEfetuado
`

<br>

### Conexões

Na atual versão do projeto estou pegando manualmente a URL do tipo de profissional que desejo me conectar no Linkedin (*posteriormente irei atualizar para que seja possível somente passar o tipo de profissional desejado e o bot pegará a url automaticamente*).

<br>

<img src="/readmeImgs/pegandoUrlConexaoDesejada.gif" alt="Pegando url de conexoes" width="525" height="360" />

<br>

Como anteriormente, você precisará adicionar tal url em [*config.properties*](/src/main/resources/config.properties).

`
linkedin.conexoes.url=urlDesejada
`

Perceba que diversas páginas de profissionais são disponibilizadas, também podemos informar ao Bot quantas dessas páginas ele deve percorrer, para isso altere para quanto deseja em [*config.properties*](/src/main/resources/config.properties) <br> **lembre-se que o Linkedin possui limite de conexões semanais**

`
linkedin.conexoes.paginas=10
`

<br>

## Run
Tudo configurado, agora é só rodar a aplicação em sua IDE desejada, com o método *MainLinkedin.java*

<br>

## Final

Caso tenha interesse ou algum feedback entre em contato comigo:
- Linkedin: https://www.linkedin.com/in/raphaelsousa9
- Email: rsousa059@gmail.com

