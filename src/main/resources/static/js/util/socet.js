import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'


var stompClient = null;
const handlers = []

export function connect() {
	const socket = new SockJS('/gs-guide-websocket');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, frame => {
		// console.log('Connected: ' + frame);
		stompClient.subscribe('/user/queue/alert', message => {
			console.log("Пришло");
			// console.log(JSON.parse(message.body).content);
			
			handlers.forEach(handler => handler(JSON.parse(message.body)))

			// console.log(JSON.parse(message.body).content);
		});
	});
}

export function addHandler(handler) {
    handlers.push(handler)
}


export function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	console.log("Disconnected");
}

export function sendMessage(message) {
	stompClient.send("/app/hello", {}, JSON.stringify(message));
}
