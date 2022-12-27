package designpatterns.creational.builder;

import lombok.Getter;

@Getter
class URL {
    //required parameters
    private final String protocol;
    private final String domainName;

    //optional parameters
    private final int portNumber;
    private final String pathParam;
    private final String queryParam;

    public URL(URLBuilder builder) {
        this.protocol = builder.protocol;
        this.domainName = builder.domainName;
        this.portNumber = builder.portNumber;
        this.pathParam = builder.pathParam;
        this.queryParam = builder.queryParam;
    }

    @Override
    public String toString() {
        return "URL is : " + protocol + domainName + ":" + portNumber + "/" + pathParam + "?" + queryParam;
    }

    static class URLBuilder {
        //required parameters
        private final String protocol;
        private final String domainName;

        //optional parameters
        private int portNumber;
        private String pathParam;
        private String queryParam;

        public URLBuilder(String protocol, String domainName) {
            this.protocol = protocol;
            this.domainName = domainName;
        }

        public URLBuilder portNumber(int portNumber) {
            this.portNumber = portNumber;
            return this;
        }

        public URLBuilder pathParam(String pathParam) {
            this.pathParam = pathParam;
            return this;
        }

        public URLBuilder queryParam(String queryParam) {
            this.queryParam = queryParam;
            return this;
        }

        public URL build() {
            return new URL(this);
        }
    }
}
