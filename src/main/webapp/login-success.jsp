<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.nagarro.imagemanagement.utils.HibernateConnection"%>
<%@page import="com.nagarro.imagemanagement.model.ImageData"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.hibernate.Query"%>
<%@page import="java.io.OutputStream"%>
<%@ page import="org.hibernate.Session"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Management Utility</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body style="background-color: #ccccff;">
	<div class="jumbotron text-center head-text"
		style="background-color: #809fff; color: white; font-size: 25px; font-weight: bold; height: 70px; padding: 1em;">
		<p>Image Management Utility</p>
		<hr
			style="height: 2px; border-width: 1; color: gray; background-color: gray; width: 100%">
	</div>
	<div class="container" style="overflow: auto; padding-top: .5em;">
		<div
			style="background-color: #ff66b3; padding-top: .5em; padding-bottom: .5em; padding-left: .5em">
			<h4>Please select an image file to upload (Max Size: 1 MB)</h4>
			<div class="form-group row">
				<form id="form" name="image-upload-form"
					action="<%=request.getContextPath()%>/fileupload" target="_self"
					method="post" enctype="multipart/form-data" data-toggle="validator"
					onsubmit="return validation()">
					<div class="custom-file col-md-8 col-sm-8">
						<input type="file" name="uploadedimage" class="custom-file-input"
							id="customFile" data-error="Select file to upload" required /> <br>
						<p class="alert-danger col-md-8 col-sm-8" role="alert"
							id="fileSizeExceed"></p>
					</div>
					<div class="custom-file col-md-1 col-sm-1">
						<input type="submit" class="btn btn-primary" value="Submit"
							formtarget="_self" />
					</div>
				</form>
				<div class="custom-file col-md-1 col-sm-1">
					<button class="btn btn-primary" onclick="clearFields()">Cancel</button>
				</div>
			</div>
		</div>
		<div class="form-group row">
			<div class="custom-file col-md-8 col-sm-8">
				<h2>Uploaded Images</h2>
			</div>
			<div class="custom-file col-md-3 col-sm-3">
				<h4 id="test"></h4>
			</div>
			<div class="custom-file col-md-1 col-sm-1"></div>
		</div>
		<div class="form-group row">
			<%
			HttpSession httpSession = request.getSession(false);
			int userId = (int) httpSession.getAttribute("userId");
			%>
			<div class="uploaded-images table-responsive">
				<form id="image_data_form" method="post" target="_blank">
					<table id="image_table" border="1"
						class="table table-md table-bordered table-striped"
						id="students-table">
						<thead class="row" id="thead-style"
							style="align: center; background-color: blue; color: white;">
							<th class="col-md-1">Sr.No.</th>
							<th class="col-md-2">Name</th>
							<th class="col-md-2">Size</th>
							<th class="col-md-4">Preview</th>
							<th class="col-md-2">Actions</th>
						</thead>
						<%
						Session s = new HibernateConnection().getConnection();
						@SuppressWarnings("unchecked")
						Query<ImageData> query = s.createQuery("from ImageData i WHERE i.userId=" + userId);
						List<ImageData> imageDataList = query.list();
						Iterator<ImageData> iterator = imageDataList.iterator();
						int serialNumber = 1;
						long totalSize = 0;
						while (iterator.hasNext()) {
							ImageData image = iterator.next();
						%>
						<tr
							style="color: black; text-align: center; border: 1px solid black;">
							<td>
								<%
								out.println(serialNumber);
								%>
							</td>
							<td id="image_name">
								<%
								out.println(image.getName());
								%>
							</td>
							<td id="image_size">
								<%
								totalSize += image.getSize();
								out.println(image.getSize());
								%>
							</td>
							<td>
								<%
								String id = String.valueOf(image.getImgId());
								%> <img src="getImage.jsp?id=<%=id%>" id="image_Preview"
								width="300px" height="150px" />
							</td>
							<td>
								<button name="editButton" value="<%=image.getImageId()%>"
									formaction="<%=request.getContextPath()%>/image-operation"
									id="editId" data-imgID="<%=image.getImageId()%>"
									class="btn btn-primary" data-target="#mymodel"
									data-toggle="modal"
									onclick="fill(<%=image.getImageId()%>, <%=image.getSize()%>, '<%=image.getName()%>')">edit</button>
								<button name="deleteButton" value="<%=image.getImageId()%>"
									class="btn btn-danger btn_remove"
									formaction="<%=request.getContextPath()%>/image-operation">
									<i class="fa fa-trash"></i>
								</button>
							</td>
						</tr>
						<%
						serialNumber++;
						}
						%>
					</table>
				</form>
			</div>
		</div>
	</div>
	<div class="modal" id="mymodel">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="text-center text-primary">Edit</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<form action="<%=request.getContextPath()%>/image-operation"
						name="edit-image-data" enctype="multipart/form-data" method="post">
						<div class="form-group">
							<input type="text" name="imageUpdateId" id="imId3"
								style="display: none;"> <label>Name: </label> <input
								type="text" name="new-name" id="imageName" class="form-control"
								required>
						</div>
						<div class="form-group">
							<label>Size: </label> <input type="text" id="imageSize"
								name="new-size" class="form-control" required disabled>
						</div>
						<div class="form-group">
							<h4>Please select an image file to upload (Max Size: 1 MB)</h4>
							<label>Image: </label> <input type="file" name="uploadednewimage"
								class="custom-file-input" id="customFile"
								data-error="Select file to upload" />
							<p class="alert-danger col-md-4 col-sm-4" role="alert"
								id="newfileSizeExceed"></p>
						</div>
						<div class="modal-footer justify-content-center">
							<button type="submit" name="imageIdForEdit" id="imageIdForEdit"
								class="btn btn-success">submit</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		function validation() {
			var img = document.forms['image-upload-form']['uploadedimage'];
			var validExt = [ "jpeg", "png", "jpg", "JPG", "PNG", "JPEG" ];
			if (img.value != '') {
				var img_ext = img.value
						.substring(img.value.lastIndexOf('.') + 1);
				var result = validExt.includes(img_ext);
				if (result) {
					if (parseFloat(img.files[0].size / (1024 * 1024)) >= 1) {
						var exceed='image size must be smaller then 1 MB. Current File size: '
								+ parseFloat(img.files[0].size / (1024 * 1024))
										.toFixed(2) + 'MB';
						document.getElementById('fileSizeExceed').innerHTML=exceed;
						return false;
					}
				} else {
					document.getElementById('fileSizeExceed').innerHTML='selected file is not an image';
					return false;
				}

			} else {
				document.getElementById('fileSizeExceed').innerHTML='No image';
				return false;
			}
			var currentSize = parseFloat(
	<%=totalSize%>
		/ (1024 * 1024))
					.toFixed(2)
					+ parseFloat(img.files[0].size / (1024 * 1024)).toFixed(2);
			console.log(currentSize);
			if (currentSize >= 0.1) {
				alert('Total image size is greater then 10 MB');
				return false;
			}
			alert('Image Uploaded Successfully');
			return true;
		}
	</script>
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
	<script>
		function clearFields() {
			form.reset();
		}
	</script>
	<script type="text/javascript">
		function fill(id,imgSize,imgName) {
			document.getElementById("imageName").value = imgName;
			document.getElementById("imageSize").value = imgSize;
			document.getElementById("imId3").value = id;
		}
	</script>
	<script type="text/javascript">
		var total =
	<%=totalSize%>
		;
		document.getElementById('test').innerHTML = "Total Size: "
				+ parseFloat(total / (1024 * 1024)).toFixed(3) + 'MB';
	</script>
	<script src="./main.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>