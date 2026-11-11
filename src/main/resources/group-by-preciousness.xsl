<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="xml" indent="yes"/>

  <xsl:key name="by-preciousness" match="gemstone" use="@preciousness"/>

  <xsl:template match="/Gem">
    <Gemstones>
      <xsl:for-each select="gemstone[
        generate-id() = generate-id(key('by-preciousness', @preciousness)[1])
      ]">
        <xsl:sort select="@preciousness"/>
        <Category name="{@preciousness}">
          <xsl:for-each select="key('by-preciousness', @preciousness)">
            <xsl:sort select="name"/>
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </Category>
      </xsl:for-each>
    </Gemstones>
  </xsl:template>

</xsl:stylesheet>
