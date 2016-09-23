# PNDF APP Mobile

## Comandos básicos do Heroku

* $heroku create -> Configura o projeto corrente baixado do github em uma app Heroku
* $heroku pg:psql --app glacial-thicket-18823 DATABASE -> Configura o banco de dados na aplicação
* $heroku local web -> Inicializa uma instancia web localmente
* $heroku pg:pull DATABASE mylocaldb --app glacial-thicket-18823 -> Realiza um pull dos dados do banco de dados para o banco local
* $heroku pg:reset DATABASE -> Reseta o banco de dados remoto
* $heroku pg:push mylocaldb DATABASE --app glacial-thicket-18823 -> Realiza um push com todos os dados do banco local para o banco remoto


## Configuração Eclipse para Debug
* Incluir variavel DATABASE_URL no debug do eclipse com o valor postgres://localhost:5432/mylocaldb

## API
* /percursoSite/:data - retorna os dados de um percurso direto do site PNDF
* /processarPercursoDia - obtem o percurso do dia no site e armazena na base de dados da App Mobile
* /percursos - obtem uma lista de percursos presentes na base de dados da App Mobile
* /percurso/:id - obtem um percurso a partir do ID