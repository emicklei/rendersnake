<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="text" />

	<xsl:variable name="doc" select="document('html4-doc.xml')/html4-doc" />

	<xsl:template match="help">
		<xsl:apply-templates select="tag" />
	</xsl:template>

	<!-- Ignore all tags that have a hash in the name, these are not regular tag definitions -->
	<xsl:template match="tag[contains(@name, '#')]" />

	<!-- Ignore duplicate <legend> definition -->
	<xsl:template match="tag[@name='legend' and @identical]" />

	<!-- Regular tag definitions -->
	<xsl:template match="tag">
		<xsl:variable name="tagName" select="@name" />
		<xsl:variable name="methodName">
			<xsl:choose>
				<xsl:when test="$tagName = 'class'">clazz</xsl:when>
				<xsl:otherwise><xsl:value-of select="$tagName" /></xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="closeMethod" select="concat('_', $methodName)" />
		<xsl:variable name="withAttributes">
			<xsl:choose>
				<xsl:when test="attribute">true</xsl:when>
				<xsl:otherwise            >false</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="description" select="$doc/tag[@name=$tagName]/@description" />
		<xsl:variable name="deprecated"  select="$doc/tag[@name=$tagName]/@deprecated"  />
		<xsl:variable name="endTag"      select="$doc/tag[@name=$tagName]/@endTag"      />
		<xsl:variable name="endTagText">
			<xsl:choose>
				<xsl:when test="$endTag = 'forbidden'">This tag must not be closed, an end tag is forbidden.</xsl:when>
				<xsl:when test="$endTag = 'optional'" >Close this tag by calling {@link #<xsl:value-of select="$closeMethod" />()} (the end tag is optional).</xsl:when>
				<xsl:otherwise                        >Close this tag by calling {@link #<xsl:value-of select="$closeMethod" />()} (the end tag is required).</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="deprecatedDocText">
			<xsl:if test="$deprecated = 'true'">&#10;     * @deprecated this tag is deprecated in HTML 4.0.</xsl:if>
		</xsl:variable>

		<xsl:text>
    /**
     * Opens the </xsl:text>
		<xsl:if test="$deprecated = 'true'">(deprecated) </xsl:if>
		<xsl:text>&lt;em></xsl:text><xsl:value-of select="$tagName" /><xsl:text>&lt;/em> tag, without any attributes.</xsl:text>
		<xsl:if test="$withAttributes = 'false'">
			<xsl:text>
     * This tag does not support any attributes.</xsl:text>
		</xsl:if>
		<xsl:text>
     *
     * &lt;p></xsl:text>
		<xsl:value-of select="$endTagText" />
		<xsl:text>
     *
     * @return the receiver, this &lt;code>HtmlCanvas&lt;/code> instance.
     * @throws IOException in case of an I/O error.</xsl:text>
		<xsl:value-of select="$deprecatedDocText" />
		<xsl:text>
     */</xsl:text>
		<xsl:if test="$deprecated = 'true'">
			<xsl:text>
    @Deprecated</xsl:text>
		</xsl:if>
		<xsl:text>
    public HtmlCanvas </xsl:text><xsl:value-of select="$methodName" /><xsl:text>() throws IOException {
        out.write("&lt;</xsl:text><xsl:value-of select="$tagName" /><xsl:text>>");
        stack.push("&lt;/</xsl:text><xsl:value-of select="$tagName" /><xsl:text>>");
        return this;
    }&#10;</xsl:text>

		<xsl:if test="$withAttributes = 'true'">
			<xsl:text>
    /**
     * Opens the &lt;em></xsl:text><xsl:value-of select="$tagName" /><xsl:text>&lt;/em> tag, with the specified attributes.
     *
     * &lt;p></xsl:text>
		<xsl:value-of select="$endTagText" />
		<xsl:text>
     *
     * &lt;p>This tag supports the following attributes:
     * &lt;dl></xsl:text>
			<xsl:apply-templates select="attribute" />
			<xsl:text>
     * &lt;/dl>
     *
     * @param attrs the {@link Attributes}, or &lt;code>null&lt;/code> if none.
     * @return the receiver, this &lt;code>HtmlCanvas&lt;/code> instance.
     * @throws IOException in case of an I/O error.</xsl:text>
		<xsl:if test="$deprecated = 'true'">
			<xsl:text>
     * @deprecated this tag is deprecated in HTML 4.0.</xsl:text>
		</xsl:if>
		<xsl:text>
     */</xsl:text>
		<xsl:if test="$deprecated = 'true'">
			<xsl:text>
    @Deprecated</xsl:text>
		</xsl:if>
		<xsl:text>
    public HtmlCanvas </xsl:text><xsl:value-of select="$methodName" /><xsl:text>(Attributes attrs) throws IOException {
        out.write("&lt;</xsl:text><xsl:value-of select="$tagName" /><xsl:text>");
        if (attrs != null)
            out.write(attrs.html());
        out.write('>');
        stack.push("&lt;/</xsl:text><xsl:value-of select="$tagName" /><xsl:text>>");
        return this;
    }

	/**
     * Closes the &lt;em></xsl:text>
		<xsl:value-of select="$tagName" />
		<xsl:text>&lt;/em> tag.
     *
     * @throws IOException in case of an I/O error while writing the end tag.
     */
    public HtmlCanvas _</xsl:text><xsl:value-of select="$methodName" /><xsl:text>() throws IOException {
        return this.close("&lt;/</xsl:text><xsl:value-of select="$tagName" /><xsl:text>>");
    }&#10;</xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="tag/attribute">
		<xsl:variable name="tagName" select="../@name" />
		<xsl:variable name="attName" select="@name" />
		<xsl:variable name="description" select="$doc/attribute[@name=$attName and context[@tag=$tagName]]/@description" />

		<xsl:text>
     * &lt;dt></xsl:text>
		<xsl:value-of select="$attName" />
		<xsl:text>&lt;dd></xsl:text>
		<xsl:value-of select="$description" />
	</xsl:template>

</xsl:stylesheet>
