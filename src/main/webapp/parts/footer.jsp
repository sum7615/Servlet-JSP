<div class="modal" id="errorModal" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Message</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>${errorMessage}</p>
				<p>${successMessage}</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<script src="<%=request.getContextPath()%>/js/jQuery/jquery-3.2.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jQuery/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jQuery/popper.min.js"></script>

<script>
    const errorMessage = "<%=request.getAttribute("status")%>";
	if (errorMessage === 'failed'| errorMessage === 'success') {
		jQuery('#errorModal').modal('show');
	}
</script>
</body>
</html>