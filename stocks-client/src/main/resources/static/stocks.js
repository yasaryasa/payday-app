let stompClient = null;
let stocks = new Map();
var _self = this;
let selectedStockCode = null;

function connect() {
	const socket = new SockJS('/websocket')
	stompClient = Stomp.over(socket)

	stompClient.connect({},
		function(frame) {
			console.log('Connected: ' + frame)
			//subscribes
			stompClient.subscribe('/topic/prices', function(stock) {
				const stockBody = JSON.parse(stock.body)
				const code = stockBody.code

				stocks.set(code, stockBody);
				if (selectedStockCode != null) {
					setStockValues(stocks.get(selectedStockCode));
				}

				//remove all
				$('#stockList > tbody').html("");
				for (let [key, value] of stocks) {
					var button = "<button type='button' class='btn red' onclick='selectStock(\"" + value.code + "\")'>Select</button>";
					const row = '<tr><td>' + value.code + '</td><td>' + value.name + '</td><td>' + Number(value.price).toFixed(2) + '</td><td>' + moment(value.priceTime).format('YYYY-MM-DD HH:mm:ss') + '</td><td>' + button + '</td></tr>'
					$('#stockList').find('tbody').prepend(row)
				}
			})

			stompClient.subscribe('/topic/comments', function(comment) {
				const commentBody = JSON.parse(comment.body)
				const fromUser = commentBody.fromUser
				const message = commentBody.message
				const timestamp = commentBody.timestamp

				const row = '<tr><td>[' + moment(timestamp).format('YYYY-MM-DD HH:mm:ss') + '] ' + fromUser + ' to all: ' + message + '</td></tr>'
				$('#comments').find('tbody').prepend(row)
			})

			stompClient.subscribe('/user/topic/comments', function(yourComment) {
				const commentBody = JSON.parse(yourComment.body)
				const fromUser = commentBody.fromUser
				const message = commentBody.message
				const timestamp = commentBody.timestamp

				const row = '<tr><td>[' + moment(timestamp).format('YYYY-MM-DD HH:mm:ss') + '] ' + fromUser + ' to you: ' + message + '</td></tr>'
				$('#comments').find('tbody').prepend(row)
			})
		},
	)
}

function selectStock(stockCode) {
	selectedStockCode = stockCode;
	setStockValues(stocks.get(selectedStockCode));
}

function setStockValues(selected) {
	$('#stockCode').val(selected.code);
	$('#stockName').val(selected.name);
	$('#stockPrice').val(selected.price);
	console.log("Stock values set : ", selected);
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect()
	}
	console.log("Disconnected")
}

$(function() {

	$('#buyForm').submit(function(e) {
		e.preventDefault();

		const stockCode = $("#stockCode").val()
		const stockName = $("#stockName").val()
		const price = $("#stockPrice").val()
		const amount = $("#stockAmount").val()

		const tx = JSON.stringify({ 'stockCode': stockCode, 'stockName': stockName, 'price': price, 'amount': amount, 'transactionType': 'BUY'  })
		$.ajax({
			url: "/transaction",
			type: "post",
			contentType: "application/json",
    		dataType: 'json',
			data: tx
		})
		.done(function(result) {
			alert("İşleminiz gerçekleşti. ID : " + result.id);
		})
		.fail(function(err) {
			alert("Hata!!! Açıklama : " + err.responseText);
		})

	})

})

connect();