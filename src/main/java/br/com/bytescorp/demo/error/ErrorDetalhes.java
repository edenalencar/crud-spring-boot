package br.com.bytescorp.demo.error;

public class ErrorDetalhes {
    private String titulo;
    private  int status;
    private String datalhes;
    private long timestamp;
    private String mensagemDesenvolvedor;
    private String campo;
    private String campoMensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDatalhes() {
        return datalhes;
    }

    public void setDatalhes(String datalhes) {
        this.datalhes = datalhes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }

    public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }

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
        private String titulo;
        private  int status;
        private String datalhes;
        private long timestamp;
        private String mensagemDesenvolvedor;
        private String campo;
        private String campoMensagem;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
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

        public Builder campo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder campoMensagem(String campoMensagem) {
            this.campoMensagem = campoMensagem;
            return this;
        }

        public ErrorDetalhes build() {
            ErrorDetalhes errorDetalhes = new ErrorDetalhes();
            errorDetalhes.setTitulo(titulo);
            errorDetalhes.setStatus(status);
            errorDetalhes.setDatalhes(datalhes);
            errorDetalhes.setTimestamp(timestamp);
            errorDetalhes.setMensagemDesenvolvedor(mensagemDesenvolvedor);
            errorDetalhes.setCampo(campo);
            errorDetalhes.setCampoMensagem(campoMensagem);
            return errorDetalhes;
        }
    }
}
