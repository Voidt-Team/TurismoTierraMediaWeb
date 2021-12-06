<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<hr>
<footer>
  <p>Welcome, <%= session.getAttribute("username") %><br>
  <a href="mailto:hege@example.com">hege@example.com</a></p>
</footer>
