package org.rendersnake.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlAttributes;

public class GeneratedAttributesTest extends TestCase {

    public void test_summary() throws IOException {
        assertEquals(" summary=\"summary\"", new HtmlAttributes().summary("summary").toHtml());
    }

    public void test_marginheight() throws IOException {
        assertEquals(" marginheight=\"marginheight\"", new HtmlAttributes().marginheight("marginheight").toHtml());
    }

    public void test_for_() throws IOException {
        assertEquals(" for=\"for\"", new HtmlAttributes().for_("for").toHtml());
    }

    public void test_accept() throws IOException {
        assertEquals(" accept=\"accept\"", new HtmlAttributes().accept("accept").toHtml());
    }

    public void test_bgcolor() throws IOException {
        assertEquals(" bgcolor=\"bgcolor\"", new HtmlAttributes().bgcolor("bgcolor").toHtml());
    }

    public void test_accept_charset() throws IOException {
        assertEquals(" accept-charset=\"accept-charset\"", new HtmlAttributes().accept_charset("accept-charset").toHtml());
    }

    public void test_scheme() throws IOException {
        assertEquals(" scheme=\"scheme\"", new HtmlAttributes().scheme("scheme").toHtml());
    }

    public void test_border() throws IOException {
        assertEquals(" border=\"border\"", new HtmlAttributes().border("border").toHtml());
    }

    public void test_vspace() throws IOException {
        assertEquals(" vspace=\"vspace\"", new HtmlAttributes().vspace("vspace").toHtml());
    }

    public void test_href() throws IOException {
        assertEquals(" href=\"href\"", new HtmlAttributes().href("href").toHtml());
    }

    public void test_onDblclick() throws IOException {
        assertEquals(" ondblclick=\"ondblclick\"", new HtmlAttributes().onDblclick("ondblclick").toHtml());
    }

    public void test_charset() throws IOException {
        assertEquals(" charset=\"charset\"", new HtmlAttributes().charset("charset").toHtml());
    }

    public void test_longdesc() throws IOException {
        assertEquals(" longdesc=\"longdesc\"", new HtmlAttributes().longdesc("longdesc").toHtml());
    }

    public void test_noshade() throws IOException {
        assertEquals(" noshade=\"noshade\"", new HtmlAttributes().noshade("noshade").toHtml());
    }

    public void test_declare() throws IOException {
        assertEquals(" declare=\"declare\"", new HtmlAttributes().declare("declare").toHtml());
    }

    public void test_content() throws IOException {
        assertEquals(" content=\"content\"", new HtmlAttributes().content("content").toHtml());
    }

    public void test_cite() throws IOException {
        assertEquals(" cite=\"cite\"", new HtmlAttributes().cite("cite").toHtml());
    }

    public void test_standby() throws IOException {
        assertEquals(" standby=\"standby\"", new HtmlAttributes().standby("standby").toHtml());
    }

    public void test_start() throws IOException {
        assertEquals(" start=\"start\"", new HtmlAttributes().start("start").toHtml());
    }

    public void test_onMousedown() throws IOException {
        assertEquals(" onmousedown=\"onmousedown\"", new HtmlAttributes().onMousedown("onmousedown").toHtml());
    }

    public void test_language() throws IOException {
        assertEquals(" language=\"language\"", new HtmlAttributes().language("language").toHtml());
    }

    public void test_nohref() throws IOException {
        assertEquals(" nohref=\"nohref\"", new HtmlAttributes().nohref("nohref").toHtml());
    }

    public void test_vlink() throws IOException {
        assertEquals(" vlink=\"vlink\"", new HtmlAttributes().vlink("vlink").toHtml());
    }

    public void test_face() throws IOException {
        assertEquals(" face=\"face\"", new HtmlAttributes().face("face").toHtml());
    }

    public void test_rev() throws IOException {
        assertEquals(" rev=\"rev\"", new HtmlAttributes().rev("rev").toHtml());
    }

    public void test_hspace() throws IOException {
        assertEquals(" hspace=\"hspace\"", new HtmlAttributes().hspace("hspace").toHtml());
    }

    public void test_link() throws IOException {
        assertEquals(" link=\"link\"", new HtmlAttributes().link("link").toHtml());
    }

    public void test_onUnload() throws IOException {
        assertEquals(" onunload=\"onunload\"", new HtmlAttributes().onUnload("onunload").toHtml());
    }

    public void test_data() throws IOException {
        assertEquals(" data=\"data\"", new HtmlAttributes().data("data").toHtml());
    }

    public void test_marginwidth() throws IOException {
        assertEquals(" marginwidth=\"marginwidth\"", new HtmlAttributes().marginwidth("marginwidth").toHtml());
    }

    public void test_accesskey() throws IOException {
        assertEquals(" accesskey=\"accesskey\"", new HtmlAttributes().accesskey("accesskey").toHtml());
    }

    public void test_version() throws IOException {
        assertEquals(" version=\"version\"", new HtmlAttributes().version("version").toHtml());
    }

    public void test_http_equiv() throws IOException {
        assertEquals(" http-equiv=\"http-equiv\"", new HtmlAttributes().http_equiv("http-equiv").toHtml());
    }

    public void test_clear() throws IOException {
        assertEquals(" clear=\"clear\"", new HtmlAttributes().clear("clear").toHtml());
    }

    public void test_valuetype() throws IOException {
        assertEquals(" valuetype=\"valuetype\"", new HtmlAttributes().valuetype("valuetype").toHtml());
    }

    public void test_defer() throws IOException {
        assertEquals(" defer=\"defer\"", new HtmlAttributes().defer("defer").toHtml());
    }

    public void test_title() throws IOException {
        assertEquals(" title=\"title\"", new HtmlAttributes().title("title").toHtml());
    }

    public void test_enctype() throws IOException {
        assertEquals(" enctype=\"enctype\"", new HtmlAttributes().enctype("enctype").toHtml());
    }

    public void test_src() throws IOException {
        assertEquals(" src=\"src\"", new HtmlAttributes().src("src").toHtml());
    }

    public void test_datetime() throws IOException {
        assertEquals(" datetime=\"datetime\"", new HtmlAttributes().datetime("datetime").toHtml());
    }

    public void test_codetype() throws IOException {
        assertEquals(" codetype=\"codetype\"", new HtmlAttributes().codetype("codetype").toHtml());
    }

    public void test_charoff() throws IOException {
        assertEquals(" charoff=\"charoff\"", new HtmlAttributes().charoff("charoff").toHtml());
    }

    public void test_onKeydown() throws IOException {
        assertEquals(" onkeydown=\"onkeydown\"", new HtmlAttributes().onKeydown("onkeydown").toHtml());
    }

    public void test_onKeypress() throws IOException {
        assertEquals(" onkeypress=\"onkeypress\"", new HtmlAttributes().onKeypress("onkeypress").toHtml());
    }

    public void test_onSubmit() throws IOException {
        assertEquals(" onsubmit=\"onsubmit\"", new HtmlAttributes().onSubmit("onsubmit").toHtml());
    }

    public void test_alink() throws IOException {
        assertEquals(" alink=\"alink\"", new HtmlAttributes().alink("alink").toHtml());
    }

    public void test_background() throws IOException {
        assertEquals(" background=\"background\"", new HtmlAttributes().background("background").toHtml());
    }

    public void test_method() throws IOException {
        assertEquals(" method=\"method\"", new HtmlAttributes().method("method").toHtml());
    }

    public void test_archive() throws IOException {
        assertEquals(" archive=\"archive\"", new HtmlAttributes().archive("archive").toHtml());
    }

    public void test_prompt() throws IOException {
        assertEquals(" prompt=\"prompt\"", new HtmlAttributes().prompt("prompt").toHtml());
    }

    public void test_rel() throws IOException {
        assertEquals(" rel=\"rel\"", new HtmlAttributes().rel("rel").toHtml());
    }

    public void test_checked() throws IOException {
        assertEquals(" checked=\"checked\"", new HtmlAttributes().checked("checked").toHtml());
    }

    public void test_readonly() throws IOException {
        assertEquals(" readonly=\"readonly\"", new HtmlAttributes().readonly("readonly").toHtml());
    }

    public void test_headers() throws IOException {
        assertEquals(" headers=\"headers\"", new HtmlAttributes().headers("headers").toHtml());
    }

    public void test_cols() throws IOException {
        assertEquals(" cols=\"cols\"", new HtmlAttributes().cols("cols").toHtml());
    }

    public void test_char_() throws IOException {
        assertEquals(" char=\"char\"", new HtmlAttributes().char_("char").toHtml());
    }

    public void test_cellpadding() throws IOException {
        assertEquals(" cellpadding=\"cellpadding\"", new HtmlAttributes().cellpadding("cellpadding").toHtml());
    }

    public void test_type() throws IOException {
        assertEquals(" type=\"type\"", new HtmlAttributes().type("type").toHtml());
    }

    public void test_cellspacing() throws IOException {
        assertEquals(" cellspacing=\"cellspacing\"", new HtmlAttributes().cellspacing("cellspacing").toHtml());
    }

    public void test_hreflang() throws IOException {
        assertEquals(" hreflang=\"hreflang\"", new HtmlAttributes().hreflang("hreflang").toHtml());
    }

    public void test_frameborder() throws IOException {
        assertEquals(" frameborder=\"frameborder\"", new HtmlAttributes().frameborder("frameborder").toHtml());
    }

    public void test_compact() throws IOException {
        assertEquals(" compact=\"compact\"", new HtmlAttributes().compact("compact").toHtml());
    }

    public void test_height() throws IOException {
        assertEquals(" height=\"height\"", new HtmlAttributes().height("height").toHtml());
    }

    public void test_maxlength() throws IOException {
        assertEquals(" maxlength=\"maxlength\"", new HtmlAttributes().maxlength("maxlength").toHtml());
    }

    public void test_onBlur() throws IOException {
        assertEquals(" onblur=\"onblur\"", new HtmlAttributes().onBlur("onblur").toHtml());
    }

    public void test_value() throws IOException {
        assertEquals(" value=\"value\"", new HtmlAttributes().value("value").toHtml());
    }

    public void test_action() throws IOException {
        assertEquals(" action=\"action\"", new HtmlAttributes().action("action").toHtml());
    }

    public void test_text() throws IOException {
        assertEquals(" text=\"text\"", new HtmlAttributes().text("text").toHtml());
    }

    public void test_colspan() throws IOException {
        assertEquals(" colspan=\"colspan\"", new HtmlAttributes().colspan("colspan").toHtml());
    }

    public void test_onMouseout() throws IOException {
        assertEquals(" onmouseout=\"onmouseout\"", new HtmlAttributes().onMouseout("onmouseout").toHtml());
    }

    public void test_width() throws IOException {
        assertEquals(" width=\"width\"", new HtmlAttributes().width("width").toHtml());
    }

    public void test_align() throws IOException {
        assertEquals(" align=\"align\"", new HtmlAttributes().align("align").toHtml());
    }

    public void test_abbr() throws IOException {
        assertEquals(" abbr=\"abbr\"", new HtmlAttributes().abbr("abbr").toHtml());
    }

    public void test_class_() throws IOException {
        assertEquals(" class=\"class\"", new HtmlAttributes().class_("class").toHtml());
    }

    public void test_onKeyup() throws IOException {
        assertEquals(" onkeyup=\"onkeyup\"", new HtmlAttributes().onKeyup("onkeyup").toHtml());
    }

    public void test_label() throws IOException {
        assertEquals(" label=\"label\"", new HtmlAttributes().label("label").toHtml());
    }

    public void test_onFocus() throws IOException {
        assertEquals(" onfocus=\"onfocus\"", new HtmlAttributes().onFocus("onfocus").toHtml());
    }

    public void test_shape() throws IOException {
        assertEquals(" shape=\"shape\"", new HtmlAttributes().shape("shape").toHtml());
    }

    public void test_code() throws IOException {
        assertEquals(" code=\"code\"", new HtmlAttributes().code("code").toHtml());
    }

    public void test_rowspan() throws IOException {
        assertEquals(" rowspan=\"rowspan\"", new HtmlAttributes().rowspan("rowspan").toHtml());
    }

    public void test_noresize() throws IOException {
        assertEquals(" noresize=\"noresize\"", new HtmlAttributes().noresize("noresize").toHtml());
    }

    public void test_size() throws IOException {
        assertEquals(" size=\"size\"", new HtmlAttributes().size("size").toHtml());
    }

    public void test_onReset() throws IOException {
        assertEquals(" onreset=\"onreset\"", new HtmlAttributes().onReset("onreset").toHtml());
    }

    public void test_rows() throws IOException {
        assertEquals(" rows=\"rows\"", new HtmlAttributes().rows("rows").toHtml());
    }

    public void test_frame() throws IOException {
        assertEquals(" frame=\"frame\"", new HtmlAttributes().frame("frame").toHtml());
    }

    public void test_onSelect() throws IOException {
        assertEquals(" onselect=\"onselect\"", new HtmlAttributes().onSelect("onselect").toHtml());
    }

    public void test_scrolling() throws IOException {
        assertEquals(" scrolling=\"scrolling\"", new HtmlAttributes().scrolling("scrolling").toHtml());
    }

    public void test_media() throws IOException {
        assertEquals(" media=\"media\"", new HtmlAttributes().media("media").toHtml());
    }

    public void test_span() throws IOException {
        assertEquals(" span=\"span\"", new HtmlAttributes().span("span").toHtml());
    }

    public void test_scope() throws IOException {
        assertEquals(" scope=\"scope\"", new HtmlAttributes().scope("scope").toHtml());
    }

    public void test_usemap() throws IOException {
        assertEquals(" usemap=\"usemap\"", new HtmlAttributes().usemap("usemap").toHtml());
    }

    public void test_object() throws IOException {
        assertEquals(" object=\"object\"", new HtmlAttributes().object("object").toHtml());
    }

    public void test_lang() throws IOException {
        assertEquals(" lang=\"lang\"", new HtmlAttributes().lang("lang").toHtml());
    }

    public void test_id() throws IOException {
        assertEquals(" id=\"id\"", new HtmlAttributes().id("id").toHtml());
    }

    public void test_selected() throws IOException {
        assertEquals(" selected=\"selected\"", new HtmlAttributes().selected("selected").toHtml());
    }

    public void test_ismap() throws IOException {
        assertEquals(" ismap=\"ismap\"", new HtmlAttributes().ismap("ismap").toHtml());
    }

    public void test_style() throws IOException {
        assertEquals(" style=\"style\"", new HtmlAttributes().style("style").toHtml());
    }

    public void test_dir() throws IOException {
        assertEquals(" dir=\"dir\"", new HtmlAttributes().dir("dir").toHtml());
    }

    public void test_alt() throws IOException {
        assertEquals(" alt=\"alt\"", new HtmlAttributes().alt("alt").toHtml());
    }

    public void test_name() throws IOException {
        assertEquals(" name=\"name\"", new HtmlAttributes().name("name").toHtml());
    }

    public void test_onMouseup() throws IOException {
        assertEquals(" onmouseup=\"onmouseup\"", new HtmlAttributes().onMouseup("onmouseup").toHtml());
    }

    public void test_nowrap() throws IOException {
        assertEquals(" nowrap=\"nowrap\"", new HtmlAttributes().nowrap("nowrap").toHtml());
    }

    public void test_multiple() throws IOException {
        assertEquals(" multiple=\"multiple\"", new HtmlAttributes().multiple("multiple").toHtml());
    }

    public void test_classid() throws IOException {
        assertEquals(" classid=\"classid\"", new HtmlAttributes().classid("classid").toHtml());
    }

    public void test_profile() throws IOException {
        assertEquals(" profile=\"profile\"", new HtmlAttributes().profile("profile").toHtml());
    }

    public void test_axis() throws IOException {
        assertEquals(" axis=\"axis\"", new HtmlAttributes().axis("axis").toHtml());
    }

    public void test_onMousemove() throws IOException {
        assertEquals(" onmousemove=\"onmousemove\"", new HtmlAttributes().onMousemove("onmousemove").toHtml());
    }

    public void test_tabindex() throws IOException {
        assertEquals(" tabindex=\"tabindex\"", new HtmlAttributes().tabindex("tabindex").toHtml());
    }

    public void test_onChange() throws IOException {
        assertEquals(" onchange=\"onchange\"", new HtmlAttributes().onChange("onchange").toHtml());
    }

    public void test_rules() throws IOException {
        assertEquals(" rules=\"rules\"", new HtmlAttributes().rules("rules").toHtml());
    }

    public void test_onMouseover() throws IOException {
        assertEquals(" onmouseover=\"onmouseover\"", new HtmlAttributes().onMouseover("onmouseover").toHtml());
    }

    public void test_coords() throws IOException {
        assertEquals(" coords=\"coords\"", new HtmlAttributes().coords("coords").toHtml());
    }

    public void test_color() throws IOException {
        assertEquals(" color=\"color\"", new HtmlAttributes().color("color").toHtml());
    }

    public void test_onLoad() throws IOException {
        assertEquals(" onload=\"onload\"", new HtmlAttributes().onLoad("onload").toHtml());
    }

    public void test_target() throws IOException {
        assertEquals(" target=\"target\"", new HtmlAttributes().target("target").toHtml());
    }

    public void test_onClick() throws IOException {
        assertEquals(" onclick=\"onclick\"", new HtmlAttributes().onClick("onclick").toHtml());
    }

    public void test_valign() throws IOException {
        assertEquals(" valign=\"valign\"", new HtmlAttributes().valign("valign").toHtml());
    }

    public void test_disabled() throws IOException {
        assertEquals(" disabled=\"disabled\"", new HtmlAttributes().disabled("disabled").toHtml());
    }

    public void test_codebase() throws IOException {
        assertEquals(" codebase=\"codebase\"", new HtmlAttributes().codebase("codebase").toHtml());
    }

 
}
