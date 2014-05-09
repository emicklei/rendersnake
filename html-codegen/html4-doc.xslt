<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="documentation">
		<html4-doc>
			<xsl:apply-templates select="node" />
		</html4-doc>
	</xsl:template>

	<!-- Tags -->
	<xsl:template match="node[@context = 'TAG']">
		<tag name="{@key}">
			<xsl:copy-of select="@description | @deprecated" />
			<xsl:attribute name="endTag">
				<xsl:choose>
					<xsl:when test="@endTag = 'F'">forbidden</xsl:when>
					<xsl:when test="@endTag = 'O'">optional</xsl:when>
					<xsl:when test="@endTag = ''" >required</xsl:when>
				</xsl:choose>
			</xsl:attribute>
		</tag>
	</xsl:template>

	<!-- Attributes -->
	<xsl:template match="node">
		<attribute name="{@key}">
			<xsl:copy-of select="@description | @deprecated" />

			<xsl:call-template name="contexts">
				<xsl:with-param name="string" select="@context" />
			</xsl:call-template>
		</attribute>
	</xsl:template>

	<xsl:template name="contexts">
		<xsl:param name="string" /> 
		<xsl:variable name="separator" select="','" /> 

		<xsl:choose>
			<xsl:when test="contains($string, $separator)">

				<xsl:variable name="first"     select="substring-before($string, $separator)" /> 
				<xsl:variable name="remaining" select="substring-after($string,  $separator)" /> 

				<context tag="{$first}" />

				<xsl:call-template name="contexts">
					<xsl:with-param name="string"    select="$remaining" /> 
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<context tag="{$string}" />
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>
