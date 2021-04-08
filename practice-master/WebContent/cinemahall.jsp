<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="ISO-8859-1">
<title>Cinema hall page</title>
<script src="js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
 jQuery( document ).ready(function() {
 	 
 //Send ajax request for show product list
 jQuery.ajax({
 url: 'rest/cinemahall/getAllCinemaHalls',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 }
 });
 jQuery( "#addcinemahall").click(function() {
	 console.log('lol');
	 //addCinemaHall/name/{cinemaname}/hallname/{cinemahallname}
 var url = 'rest/cinemahall/addCinemaHall/name/' +
 jQuery("#cinemaname").val() + '/hallname/' +
 jQuery("#cinemahallname").val();
 //Send ajax request for adding new product
 jQuery.ajax({
 url: url,
 dataType: 'json',
 type: 'PUT',
 success: function(data) {
 //Send ajax request for refresh product list after adding product
 jQuery.ajax({
 url: 'rest/cinemahall/getAllCinemaHalls',
 dataType: 'json',
 type: 'GET',
 success: function(data) {
 jQuery('.datarow').remove();
 jQuery('#headerrow').after(createDataRowsFromJson(data));
 jQuery("#cinemaname").val('');
 jQuery("#cinemahallname").val('');
 }
 });
 }
 });
 });
 });
 function createCustomDataRowsFromJson(data) {
	 var tableContent1 = "";
	 for (var key in data) {
	 if (data.hasOwnProperty(key)) {
	 tableContent1 = tableContent1 + "<tr class='datarow'>";
	 tableContent1 = tableContent1 + "<td>";
	 tableContent1 = tableContent1 + data[key].id;
	 tableContent1 = tableContent1 + "</td>";
	 tableContent1 = tableContent1 + "<td>";
	 tableContent1 = tableContent1 + data[key].fullName;
	 tableContent1 = tableContent1 + "</td>";
	 tableContent1 = tableContent1 + "<td>";
	 tableContent1 = tableContent1 + data[key].age;
	 tableContent1 = tableContent1 + "</td>";
	 tableContent1 = tableContent1 + "<td>";
	 tableContent1 = tableContent1 + data[key].cinemaHall.id;
	 tableContent1 = tableContent1 + "</td>";
	 tableContent1 = tableContent1 + "</tr>";
	
	
	 }
	
	 }
	 return tableContent1;
	 }
 
 
 function createDataRowsFromJson(data) {
 var tableContent = "";
 for (var key in data) {
 if (data.hasOwnProperty(key)) {
	 
 tableContent = tableContent + "<tr class='datarow'>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].id;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].hallName;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + data[key].cinemaName;
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "<td>";
 tableContent = tableContent + "<input type='button' onclick='deleteCinemaHall(" + data[key].id + ")' value='Delete cinemhall'/>";
 tableContent = tableContent + "<input type='button' onclick='editCinemaHall(this)' value='Edit cinemahall'/>";
 tableContent = tableContent + "<input type='button' onclick='getTopTenVisitors(" + data[key].id + ")' value='Get top ten visitors'/>";
 tableContent = tableContent + "</td>";
 tableContent = tableContent + "</tr>";
 }
 }
 return tableContent;
 }
 function deleteCinemaHall(id) {
 var url = 'rest/cinemahall/deleteCinemaHall/' + id;
 //Send ajax request for deleting product
 jQuery.ajax({
 url: url,

 dataType: 'json',
 type: 'DELETE',
 success: function(data) {
 jQuery.ajax({
 url: 'rest/cinemahall/getAllCinemaHalls',
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
 function editCinemaHall(button) {
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 1){
 jQuery(this).html("<input type='text' id='editname' value='" +
 jQuery(this).text() + "'/>");
 } else if(index == 2){
 jQuery(this).html("<input type='text' id='edithallname' value='" +
 jQuery(this).text() + "'/>");
 } else if (index == 3){
 var actionHtml = "<input type='button' onclick='applyEditCinemaHall(this)' value='Update cinem hall'/>"
 actionHtml = actionHtml + "<input type='button' onclick='cancelEdit(this)' value='Cancel edit'/>"
 jQuery(this).html(actionHtml);
 }
 });
 }
 function getTopTenVisitors (id) {
	 
	 var url = 'rest/cinemahall/getTopTenVisitors/id/' + id;
	 jQuery("#topten").val();
 jQuery.ajax({
	 url: url,
	 dataType: 'json',
	 type: 'GET',
	 success: function(data) {
	 jQuery('#header').after(createCustomDataRowsFromJson(data));
	 }
 });
 }
 function cancelEdit(button) {
 var id;
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 0){
 id = jQuery(this).text();
 } else if(index != 0 && index != 3){
 jQuery(this).html(jQuery(this).find('input').val());
 } else if (index == 3){
 
 }
 });
 }
 function applyEditCinemaHall(button){
 var id, hallname, cinemaname;
 jQuery(button).closest('tr').children().each(function(index, value){
 if(index == 0){
 id = jQuery(this).text();
 } else if(index == 1){
 hallname = jQuery(this).find('input').val();
 } else if (index == 2){
 cinemaname = jQuery(this).find('input').val();
 }
 });
 //Send ajax request for upating product
 //"updateCinemaHall/id/{cinemahallid}/name/{cinemaname}/hallname/{cinemahallname}"
 var url = 'rest/cinemahall/updateCinemaHall/id/' + id + '/name/' + cinemaname +
 '/hallname/' + hallname;
 jQuery.ajax({
 url: url,
 dataType: 'json',
 type: 'POST',
 success: function(data) {
 jQuery.ajax({
 url: 'rest/cinemahall/getAllCinemaHalls',
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
 	<h1>Cinemahall Page</h1>
 	<h2><a href=visitor.jsp> Visitor Page</a></h2>
 <table style="width: 1600px" border="1" id="cinemahalltable">
 <tr id="headerrow">
 <td>Id</td>
 <td>Cinemahall Name</td>
 <td>Cinema name</td>
 <td>Action</td>
 </tr>
 <tr>
 <td></td>
 <td><input type="text" name="cinemahallname" id="cinemahallname" /></td>
 <td><input type="text" name="cinemaname"
 id="cinemaname" /></td>
 <td><input style="font-size: 20px;" type="button" name="addcinemahall" id="addcinemahall"
 value="Add cinemahall"></td>
 </tr>
 </table>
 
 
 <h1>Top ten visitors</h1>
 
  <table style="width: 80%" border="1" id="topten">
    <tr id="header">
      <td>Id</td>
      <td>Fullname</td>
      <td>Age</td>
      <td>Cinemahall id</td>
    </tr>
  </table>
  
  
</body>
<footer>

<div class="footer">© 2020 Porta Two Company.&nbsp; &nbsp; &nbsp; All rights reserved. &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
serg13853@gmail.com</div>
</footer>
</html>