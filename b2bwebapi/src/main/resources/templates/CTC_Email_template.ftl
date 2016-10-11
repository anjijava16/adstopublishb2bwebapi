<!doctype html>
<html>
	<head>
	<meta name="viewport" content="width=device-width">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<style>
	p{margin:0;}

	</style>
	</head>

	<body bgcolor="#f6f6f6">
		<div  bgcolor="#f6f6f6">
			
			  <div class="content">
				<div>
					<span style="margin-bottom: 20px;">
						<p>Dear ${name},</p>
						<p><b>Congratulations!</b></p>
						<#if designation != "null">
							Since your performance was exemplary! You have been promoted as <b>${designation}</b> effective from <b>${from}</b>.
						</#if>
						
						<p>We are pleased to revise the compensation and benefits program applicable to you with effect from <b>${from}</b>
							and your revised salary would be Rs.<b>${ctc}</b>
						<p>The other terms and conditions of your service continue to remain unchanged</p>
						<p>Kindly collect the revised compensation document from your HR</p>
						<p>If you have any payroll related queries, kindly revert to payroll@sapatalabs.com</p>
						<p style="text-align: left;">***   confidential    ***</p><br/>
					</span>
					
					<div><p>Regards</p>
					<p> Payroll Team </p>
					<img src="http://www.saptalabs.com/images/logo_color.png"></img>
					<p>Email: payroll@sapatalabs.com </p>
					<p>Phone: +91-80-41169612</p></div>
				</div>
			  </div>
			
		</div>
	</body>
</html>
