package br.com.bytescorp.demo.error;

public class RecursoNaoEncontradoDetalhes extends ErrorDetalhes{

    public static final class RecursoNaoEncontradoDetalhesBuilder {
        private String titulo;
        private  int status;
        private String datalhes;
        private long timestamp;
        private String mensagemDesenvolvedor;

        private RecursoNaoEncontradoDetalhesBuilder() {
        }

        public static RecursoNaoEncontradoDetalhesBuilder newBuilder() {
            return new RecursoNaoEncontradoDetalhesBuilder();
        }

        public RecursoNaoEncontradoDetalhesBuilder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public RecursoNaoEncontradoDetalhesBuilder status(int status) {
            this.status = status;
            return this;
        }

        public RecursoNaoEncontradoDetalhesBuilder datalhes(String datalhes) {
            this.datalhes = datalhes;
            return this;
        }

        public RecursoNaoEncontradoDetalhesBuilder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RecursoNaoEncontradoDetalhesBuilder mensagemDesenvolvedor(String mensagemDesenvolvedor) {
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
            return this;
        }

        public RecursoNaoEncontradoDetalhes build() {
            RecursoNaoEncontradoDetalhes recursoNaoEncontradoDetalhes = new RecursoNaoEncontradoDetalhes();
            recursoNaoEncontradoDetalhes.setDatalhes(this.datalhes);
            recursoNaoEncontradoDetalhes.setStatus(this.status);
            recursoNaoEncontradoDetalhes.setMensagemDesenvolvedor(this.mensagemDesenvolvedor);
            recursoNaoEncontradoDetalhes.setTimestamp(timestamp);
            recursoNaoEncontradoDetalhes.setTitulo(titulo);
            return recursoNaoEncontradoDetalhes;
        }
    }
}
