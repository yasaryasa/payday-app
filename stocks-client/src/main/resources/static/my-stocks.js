
$(function() {

	$('.sellBtn').click(function() {
		var row = [];
		$(this).closest('tr').find('td').not(':last').each(function() {
			var textval = $(this).text();
			row.push(textval);
		});
		$('#stockCode').val(row[0]);
		$('#stockName').val(row[1]);
		$('#stockAmount').val(row[2]);
		$('#stockPrice').val(row[3]);
	});

	$('#sellForm').submit(function(e) {
		e.preventDefault();

		const stockCode = $("#stockCode").val()
		const stockName = $("#stockName").val()
		const price = $("#stockPrice").val()
		const amount = $("#stockAmount").val()

		const tx = JSON.stringify({ 'stockCode': stockCode, 'stockName': stockName, 'price': price, 'amount': amount, 'transactionType': 'SELL' })
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