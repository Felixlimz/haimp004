$(document).ready( function () {
	 var table = $('#employeesTable').DataTable({
			"sAjaxSource": "/api/borrow",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
				{ "mData": "id" },
				{ "mData": "userName" },
				{ "mData": "userEmail" },
				{ "mData": "bookName" },
				{ "mData": "borrowDate" },
			],
	 })
});