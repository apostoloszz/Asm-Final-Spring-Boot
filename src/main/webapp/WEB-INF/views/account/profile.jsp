<h2>Account Profile</h2>
<p>Username: ${customer.user_name}</p>
<p>Email: ${customer.email}</p>
<p>Name: ${customer.name}</p>
<p>Address: ${customer.address}</p>
<p>Phone: ${customer.phone}</p>

<a href="${pageContext.request.contextPath}/account/edit/${customer.id}">Edit Profile</a>
