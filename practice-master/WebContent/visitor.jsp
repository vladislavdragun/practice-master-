<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Visitor page</title>
<script src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
 jQuery( document ).ready(function() {
 //Send ajax request for show product list
 jQuery.ajax({
 url: 'rest/visior/getAllVisitors',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 }
 });
 jQuery( "#addvisitor").click(function() {
	 console.log('lol');
	 //addVisitor/name/{fullname}/age/{age}/cinemahallid/{cinemahallid}
 var url = 'rest/visitor/addvisitor/name/' +
 jQuery("#fullname").val() + '/age/' +
 jQuery("#age").val() + '/cinemahallid/' + 
 jQuery("#cinemahallid").val();
 //Send ajax request for adding new product
 jQuery.ajax({
 url: url,
 dataType: 'json',
 type: 'PUT',
 success: function(data) {
 //Send ajax request for refresh product list after adding product
 jQuery.ajax({
 url: 'rest/visitor/getAllVisitors',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('.datarow').remove();
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 jQuery("#fullname").val('');
 jQuery("#age").val('');
 jQuery("#cinemahallid").val('');
 }
 });
 }
 });
 });
 });
 function createDataRowsFromJson(data) {
 var tableContent = "";
 for (var key in data) {
 if (data.hasOwnProperty(key)) {
 tableContent = tableContent + "<tr class='datarow'>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].id;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].fullName;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].age;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].cinemaHall.id;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + "<input type='button' onclick='deleteVisitor(" + data[key].id + ")' value='Delete visitor'/>";
 tableContent = tableContent + "<input type='button' onclick='editVisitor(this)' value='Edit visitor'/>";
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "</tr>";
 }
 }
 return tableContent;
 }
 function deleteVisitor(id) {
 var url = 'rest/visitor/deleteVisitor/' + id;
 //Send ajax request for deleting product
 jQuery.ajax({
 url: url,

 dataType: 'json',
 type: 'DELETE',
 success: function(data) {
 jQuery.ajax({
 url: 'rest/visitor/getAllVisitors',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('.datarow').remove();
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 }
 });
 }
 });
 }
 function editVisitor(button) {
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 1){
 jQuery(this).html("<input type='text' id='editname' value='" +
 jQuery(this).text() + "'/>");
 } else if(index == 2){
 jQuery(this).html("<input type='text' id='editage' value='" +
 jQuery(this).text() + "'/>");
 } else if(index == 3){
 jQuery(this).html("<input type='text' id='editcinemahallid' value='" +
 jQuery(this).text() + "'/>");
 } else if (index == 4){
 var actionHtml = "<input type='button' onclick='applyEditVisitor(this)' value='Update visitor'/>"
 actionHtml = actionHtml + "<input type='button' onclick='cancelEdit(this)' value='Cancel edit'/>"
 jQuery(this).html(actionHtml);
 }
 });
 }
 function cancelEdit(button) {
 var id;
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 0){
 id = jQuery(this).text();
 } else if(index != 0 && index != 4){
 jQuery(this).html(jQuery(this).find('input').val());
 } else if (index == 4){
 var actionHtml = "<input type='button' onclick='deleteVisitor(" + id + ")' value='Delete visitor'/>"
 actionHtml = actionHtml + "<input type='button' onclick='editVisitor(this)' value='Edit visitor'/>"
 jQuery(this).html(actionHtml);
 }
 });
 }
 function applyEditVisitor(button){
 var id, fullname, age, cinemahallid;
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 0){
 id = jQuery(this).text();
 } else if(index == 1){
 fullname = jQuery(this).find('input').val();
 } else if (index == 2){
 age = jQuery(this).find('input').val();
 }else if (index == 3){
 cinemahallid = jQuery(this).find('input').val();	 
 }
 });
 //Send ajax request for upating product
 //updateVisitor/id/{visitorid}/name/{fullname}/age/{age}/cinemahallid/{cinemahallid}
 var url = 'rest/visitor/updateVisitor/id/' + id + '/name/' + fullname +
 '/age/' + age + '/cinemahallid/' + cinemahallid;
 jQuery.ajax({
 url: url,
 dataType: 'json',
 type: 'POST',
 success: function(data) {
 jQuery.ajax({
 url: 'rest/visitor/getAllVisitors',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('.datarow').remove();
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 }
 });
 }
 });
 }
</script>
</head>
<body>
<div class="header">
 	<img  id="logo" src="https://multiplex.ua/img/menu_logo.png">
 	</div>
 <h1>Visitor page</h1>
 <h2><a href=cinemahall.jsp> Cinemahall Page</a></h2>
 <table style="width: 70%" border="1" id="visitortable">
 <tr id="headerrow">
 <td>Id</td>
 <td>Fullname</td>
 <td>Age</td>
 <td>Cinemahall id</td>
 <td>Action</td>
 </tr>
 <tr>
 <td></td>
 <td><input type="text" name="fullname" id="fullname" /></td>
 <td><input type="text" name="age"
 id="age" /></td>
 <td><input type="text" name="cinemahallid" id="cinemahallid" /></td>
 <td><input type="button" name="addvisitor" id="addvisitor"
 value="Add visitor"></td>
 </tr>
 </table>
</body>
<footer>

<div class="footer">© 2020 Porta Two Company.&nbsp; &nbsp; &nbsp; All rights reserved. &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
serg13853@gmail.com</div>
</footer>
</html>