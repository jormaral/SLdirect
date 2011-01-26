<%@ include file="/WEB-INF/jsp/includes.jsp" %>

								</td>
							</tr>
							<tr>
								<td style="background:#525252;padding:6px;color:#929292;font-weight:bold">
									<a href="<spring:url value="/terminos"/>" style="color:black;border-bottom-color:black"><fmt:message key="footer.terms"/></a>  | 
									<a href="<spring:url value="/privacidad"/>" style="color:black;border-bottom-color:black"><fmt:message key="footer.priv"/></a> | 
									<a href="http://www.cenatic.es" style="color:black;border-bottom-color:black"><fmt:message key="footer.about"/> </a>
								</td>
							</tr>
							<tr>
								<td style="background:#222222;padding:6px;color:#929292;font-weight:bold;">
									<fmt:message key="footer.copyright"/>
								</td>
							</tr>
						</table>
						<br /><br />
				</td>
				<td style="text-align:left;vertical-align:top;padding-top:8px;padding-left:0px;padding-right:0px;background:url(<spring:url value="/static/images/rightborder.png"/>);background-position:-1px 8px;background-repeat:no-repeat;width:22px;">
				</td>
			</tr>
		</table>
	</div>	

<%--
	global $loginPage;
	if (!$loginPage)
		echo "</div></td></tr></table>";
--%>

	</body>
</html>	
