package redis.with.reactor;

class Coffee {
    private String id;
    private String name;

    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coffee setId(String id) {
        this.id = id;
        return this;
    }

    public Coffee setName(String name) {
        this.name = name;
        return this;
    }
}
