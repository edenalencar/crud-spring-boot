package br.com.bytescorp.demo.error;

public class ValidacaoErrorDetalhes extends ErrorDetalhes{
    private String campo;
    private String campoMensagem;

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getCampoMensagem() {
        return campoMensagem;
    }

    public void setCampoMensagem(String campoMensagem) {
        this.campoMensagem = campoMensagem;
    }

    public static final class Builder {
        private String campo;
        private String campoMensagem;
        private String titulo;
        private  int status;
        private String datalhes;
        private long timestamp;
        private String mensagemDesenvolvedor;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder campo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder campoMensagem(String campoMensagem) {
            this.campoMensagem = campoMensagem;
            return this;
        }

        public Builder titulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder datalhes(String datalhes) {
            this.datalhes = datalhes;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder mensagemDesenvolvedor(String mensagemDesenvolvedor) {
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
            return this;
        }

        public ValidacaoErrorDetalhes build() {
            ValidacaoErrorDetalhes validacaoErrorDetalhes = new ValidacaoErrorDetalhes();
            validacaoErrorDetalhes.setTitulo(titulo);
            validacaoErrorDetalhes.setStatus(status);
            validacaoErrorDetalhes.setDatalhes(datalhes);
            validacaoErrorDetalhes.setTimestamp(timestamp);
            validacaoErrorDetalhes.setMensagemDesenvolvedor(mensagemDesenvolvedor);
            validacaoErrorDetalhes.campo = this.campo;
            validacaoErrorDetalhes.campoMensagem = this.campoMensagem;
            return validacaoErrorDetalhes;
        }
    }
}
