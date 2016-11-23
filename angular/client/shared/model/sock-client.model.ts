export class SockClient {

    socket: any;
    client: any;

    constructor(url: string, topic: string, callback: Function) {
        this.socket = new SockJS(url);
        this.client = Stomp.over(this.socket);
        this.client.debug = null;
        this.client.server_heartbeat_interval = 3600000;
        this.client.connect({}, () => {
            this.client.subscribe(topic, (message) => {
                callback(message);
            })
        })
    }

    disconnect() {
        this.client.disconnect();
    }

}
