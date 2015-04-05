package org.rendersnake.test;

import java.io.IOException;

import junit.framework.TestCase;

import org.rendersnake.HtmlAttributesFactory;

public class GeneratedAttributesFactoryTest extends TestCase {
    public void test_summary() throws IOException {
        assertEquals(" summary=\"summary\"", HtmlAttributesFactory.summary("summary").toHtml());
    }

    public void test_marginheight() throws IOException {
        assertEquals(" marginheight=\"marginheight\"", HtmlAttributesFactory.marginheight("marginheight").toHtml());
    }

    public void test_for_() throws IOException {
        assertEquals(" for=\"for\"", HtmlAttributesFactory.for_("for").toHtml());
    }

    public void test_accept() throws IOException {
        assertEquals(" accept=\"accept\"", HtmlAttributesFactory.accept("accept").toHtml());
    }

    public void test_bgcolor() throws IOException {
        assertEquals(" bgcolor=\"bgcolor\"", HtmlAttributesFactory.bgcolor("bgcolor").toHtml());
    }

    public void test_accept_charset() throws IOException {
        assertEquals(" accept-charset=\"accept-charset\"", HtmlAttributesFactory.accept_charset("accept-charset").toHtml());
    }

    public void test_scheme() throws IOException {
        assertEquals(" scheme=\"scheme\"", HtmlAttributesFactory.scheme("scheme").toHtml());
    }

    public void test_border() throws IOException {
        assertEquals(" border=\"border\"", HtmlAttributesFactory.border("border").toHtml());
    }

    public void test_vspace() throws IOException {
        assertEquals(" vspace=\"vspace\"", HtmlAttributesFactory.vspace("vspace").toHtml());
    }

    public void test_href() throws IOException {
        assertEquals(" href=\"href\"", HtmlAttributesFactory.href("href").toHtml());
    }

    public void test_onDblclick() throws IOException {
        assertEquals(" ondblclick=\"ondblclick\"", HtmlAttributesFactory.onDblclick("ondblclick").toHtml());
    }

    public void test_charset() throws IOException {
        assertEquals(" charset=\"charset\"", HtmlAttributesFactory.charset("charset").toHtml());
    }

    public void test_longdesc() throws IOException {
        assertEquals(" longdesc=\"longdesc\"", HtmlAttributesFactory.longdesc("longdesc").toHtml());
    }

    public void test_noshade() throws IOException {
        assertEquals(" noshade=\"noshade\"", HtmlAttributesFactory.noshade("noshade").toHtml());
    }

    public void test_declare() throws IOException {
        assertEquals(" declare=\"declare\"", HtmlAttributesFactory.declare("declare").toHtml());
    }

    public void test_content() throws IOException {
        assertEquals(" content=\"content\"", HtmlAttributesFactory.content("content").toHtml());
    }

    public void test_cite() throws IOException {
        assertEquals(" cite=\"cite\"", HtmlAttributesFactory.cite("cite").toHtml());
    }

    public void test_standby() throws IOException {
        assertEquals(" standby=\"standby\"", HtmlAttributesFactory.standby("standby").toHtml());
    }

    public void test_start() throws IOException {
        assertEquals(" start=\"start\"", HtmlAttributesFactory.start("start").toHtml());
    }

    public void test_onMousedown() throws IOException {
        assertEquals(" onmousedown=\"onmousedown\"", HtmlAttributesFactory.onMousedown("onmousedown").toHtml());
    }

    public void test_language() throws IOException {
        assertEquals(" language=\"language\"", HtmlAttributesFactory.language("language").toHtml());
    }

    public void test_nohref() throws IOException {
        assertEquals(" nohref=\"nohref\"", HtmlAttributesFactory.nohref("nohref").toHtml());
    }

    public void test_vlink() throws IOException {
        assertEquals(" vlink=\"vlink\"", HtmlAttributesFactory.vlink("vlink").toHtml());
    }

    public void test_face() throws IOException {
        assertEquals(" face=\"face\"", HtmlAttributesFactory.face("face").toHtml());
    }

    public void test_rev() throws IOException {
        assertEquals(" rev=\"rev\"", HtmlAttributesFactory.rev("rev").toHtml());
    }

    public void test_hspace() throws IOException {
        assertEquals(" hspace=\"hspace\"", HtmlAttributesFactory.hspace("hspace").toHtml());
    }

    public void test_link() throws IOException {
        assertEquals(" link=\"link\"", HtmlAttributesFactory.link("link").toHtml());
    }

    public void test_onUnload() throws IOException {
        assertEquals(" onunload=\"onunload\"", HtmlAttributesFactory.onUnload("onunload").toHtml());
    }

    public void test_data() throws IOException {
        assertEquals(" data=\"data\"", HtmlAttributesFactory.data("data").toHtml());
    }

    public void test_marginwidth() throws IOException {
        assertEquals(" marginwidth=\"marginwidth\"", HtmlAttributesFactory.marginwidth("marginwidth").toHtml());
    }

    public void test_accesskey() throws IOException {
        assertEquals(" accesskey=\"accesskey\"", HtmlAttributesFactory.accesskey("accesskey").toHtml());
    }

    public void test_version() throws IOException {
        assertEquals(" version=\"version\"", HtmlAttributesFactory.version("version").toHtml());
    }

    public void test_http_equiv() throws IOException {
        assertEquals(" http-equiv=\"http-equiv\"", HtmlAttributesFactory.http_equiv("http-equiv").toHtml());
    }

    public void test_clear() throws IOException {
        assertEquals(" clear=\"clear\"", HtmlAttributesFactory.clear("clear").toHtml());
    }

    public void test_valuetype() throws IOException {
        assertEquals(" valuetype=\"valuetype\"", HtmlAttributesFactory.valuetype("valuetype").toHtml());
    }

    public void test_defer() throws IOException {
        assertEquals(" defer=\"defer\"", HtmlAttributesFactory.defer("defer").toHtml());
    }

    public void test_title() throws IOException {
        assertEquals(" title=\"title\"", HtmlAttributesFactory.title("title").toHtml());
    }

    public void test_enctype() throws IOException {
        assertEquals(" enctype=\"enctype\"", HtmlAttributesFactory.enctype("enctype").toHtml());
    }

    public void test_src() throws IOException {
        assertEquals(" src=\"src\"", HtmlAttributesFactory.src("src").toHtml());
    }

    public void test_datetime() throws IOException {
        assertEquals(" datetime=\"datetime\"", HtmlAttributesFactory.datetime("datetime").toHtml());
    }

    public void test_codetype() throws IOException {
        assertEquals(" codetype=\"codetype\"", HtmlAttributesFactory.codetype("codetype").toHtml());
    }

    public void test_charoff() throws IOException {
        assertEquals(" charoff=\"charoff\"", HtmlAttributesFactory.charoff("charoff").toHtml());
    }

    public void test_onKeydown() throws IOException {
        assertEquals(" onkeydown=\"onkeydown\"", HtmlAttributesFactory.onKeydown("onkeydown").toHtml());
    }

    public void test_onKeypress() throws IOException {
        assertEquals(" onkeypress=\"onkeypress\"", HtmlAttributesFactory.onKeypress("onkeypress").toHtml());
    }

    public void test_onSubmit() throws IOException {
        assertEquals(" onsubmit=\"onsubmit\"", HtmlAttributesFactory.onSubmit("onsubmit").toHtml());
    }

    public void test_alink() throws IOException {
        assertEquals(" alink=\"alink\"", HtmlAttributesFactory.alink("alink").toHtml());
    }

    public void test_background() throws IOException {
        assertEquals(" background=\"background\"", HtmlAttributesFactory.background("background").toHtml());
    }

    public void test_method() throws IOException {
        assertEquals(" method=\"method\"", HtmlAttributesFactory.method("method").toHtml());
    }

    public void test_archive() throws IOException {
        assertEquals(" archive=\"archive\"", HtmlAttributesFactory.archive("archive").toHtml());
    }

    public void test_prompt() throws IOException {
        assertEquals(" prompt=\"prompt\"", HtmlAttributesFactory.prompt("prompt").toHtml());
    }

    public void test_rel() throws IOException {
        assertEquals(" rel=\"rel\"", HtmlAttributesFactory.rel("rel").toHtml());
    }

    public void test_checked() throws IOException {
        assertEquals(" checked=\"checked\"", HtmlAttributesFactory.checked("checked").toHtml());
    }

    public void test_readonly() throws IOException {
        assertEquals(" readonly=\"readonly\"", HtmlAttributesFactory.readonly("readonly").toHtml());
    }

    public void test_headers() throws IOException {
        assertEquals(" headers=\"headers\"", HtmlAttributesFactory.headers("headers").toHtml());
    }

    public void test_cols() throws IOException {
        assertEquals(" cols=\"cols\"", HtmlAttributesFactory.cols("cols").toHtml());
    }

    public void test_char_() throws IOException {
        assertEquals(" char=\"char\"", HtmlAttributesFactory.char_("char").toHtml());
    }

    public void test_cellpadding() throws IOException {
        assertEquals(" cellpadding=\"cellpadding\"", HtmlAttributesFactory.cellpadding("cellpadding").toHtml());
    }

    public void test_type() throws IOException {
        assertEquals(" type=\"type\"", HtmlAttributesFactory.type("type").toHtml());
    }

    public void test_cellspacing() throws IOException {
        assertEquals(" cellspacing=\"cellspacing\"", HtmlAttributesFactory.cellspacing("cellspacing").toHtml());
    }

    public void test_hreflang() throws IOException {
        assertEquals(" hreflang=\"hreflang\"", HtmlAttributesFactory.hreflang("hreflang").toHtml());
    }

    public void test_frameborder() throws IOException {
        assertEquals(" frameborder=\"frameborder\"", HtmlAttributesFactory.frameborder("frameborder").toHtml());
    }

    public void test_compact() throws IOException {
        assertEquals(" compact=\"compact\"", HtmlAttributesFactory.compact("compact").toHtml());
    }

    public void test_height() throws IOException {
        assertEquals(" height=\"height\"", HtmlAttributesFactory.height("height").toHtml());
    }

    public void test_maxlength() throws IOException {
        assertEquals(" maxlength=\"maxlength\"", HtmlAttributesFactory.maxlength("maxlength").toHtml());
    }

    public void test_onBlur() throws IOException {
        assertEquals(" onblur=\"onblur\"", HtmlAttributesFactory.onBlur("onblur").toHtml());
    }

    public void test_value() throws IOException {
        assertEquals(" value=\"value\"", HtmlAttributesFactory.value("value").toHtml());
    }

    public void test_action() throws IOException {
        assertEquals(" action=\"action\"", HtmlAttributesFactory.action("action").toHtml());
    }

    public void test_text() throws IOException {
        assertEquals(" text=\"text\"", HtmlAttributesFactory.text("text").toHtml());
    }

    public void test_colspan() throws IOException {
        assertEquals(" colspan=\"colspan\"", HtmlAttributesFactory.colspan("colspan").toHtml());
    }

    public void test_onMouseout() throws IOException {
        assertEquals(" onmouseout=\"onmouseout\"", HtmlAttributesFactory.onMouseout("onmouseout").toHtml());
    }

    public void test_width() throws IOException {
        assertEquals(" width=\"width\"", HtmlAttributesFactory.width("width").toHtml());
    }

    public void test_align() throws IOException {
        assertEquals(" align=\"align\"", HtmlAttributesFactory.align("align").toHtml());
    }

    public void test_abbr() throws IOException {
        assertEquals(" abbr=\"abbr\"", HtmlAttributesFactory.abbr("abbr").toHtml());
    }

    public void test_class_() throws IOException {
        assertEquals(" class=\"class\"", HtmlAttributesFactory.class_("class").toHtml());
    }

    public void test_onKeyup() throws IOException {
        assertEquals(" onkeyup=\"onkeyup\"", HtmlAttributesFactory.onKeyup("onkeyup").toHtml());
    }

    public void test_label() throws IOException {
        assertEquals(" label=\"label\"", HtmlAttributesFactory.label("label").toHtml());
    }
    
    public void test_placeholder() throws IOException {
        assertEquals(" placeholder=\"placeholder\"", HtmlAttributesFactory.placeholder("placeholder").toHtml());
    }

    public void test_onFocus() throws IOException {
        assertEquals(" onfocus=\"onfocus\"", HtmlAttributesFactory.onFocus("onfocus").toHtml());
    }

    public void test_shape() throws IOException {
        assertEquals(" shape=\"shape\"", HtmlAttributesFactory.shape("shape").toHtml());
    }

    public void test_code() throws IOException {
        assertEquals(" code=\"code\"", HtmlAttributesFactory.code("code").toHtml());
    }

    public void test_rowspan() throws IOException {
        assertEquals(" rowspan=\"rowspan\"", HtmlAttributesFactory.rowspan("rowspan").toHtml());
    }

    public void test_noresize() throws IOException {
        assertEquals(" noresize=\"noresize\"", HtmlAttributesFactory.noresize("noresize").toHtml());
    }

    public void test_size() throws IOException {
        assertEquals(" size=\"size\"", HtmlAttributesFactory.size("size").toHtml());
    }

    public void test_onReset() throws IOException {
        assertEquals(" onreset=\"onreset\"", HtmlAttributesFactory.onReset("onreset").toHtml());
    }

    public void test_rows() throws IOException {
        assertEquals(" rows=\"rows\"", HtmlAttributesFactory.rows("rows").toHtml());
    }

    public void test_frame() throws IOException {
        assertEquals(" frame=\"frame\"", HtmlAttributesFactory.frame("frame").toHtml());
    }

    public void test_onSelect() throws IOException {
        assertEquals(" onselect=\"onselect\"", HtmlAttributesFactory.onSelect("onselect").toHtml());
    }

    public void test_scrolling() throws IOException {
        assertEquals(" scrolling=\"scrolling\"", HtmlAttributesFactory.scrolling("scrolling").toHtml());
    }

    public void test_media() throws IOException {
        assertEquals(" media=\"media\"", HtmlAttributesFactory.media("media").toHtml());
    }

    public void test_span() throws IOException {
        assertEquals(" span=\"span\"", HtmlAttributesFactory.span("span").toHtml());
    }

    public void test_scope() throws IOException {
        assertEquals(" scope=\"scope\"", HtmlAttributesFactory.scope("scope").toHtml());
    }

    public void test_usemap() throws IOException {
        assertEquals(" usemap=\"usemap\"", HtmlAttributesFactory.usemap("usemap").toHtml());
    }

    public void test_object() throws IOException {
        assertEquals(" object=\"object\"", HtmlAttributesFactory.object("object").toHtml());
    }

    public void test_lang() throws IOException {
        assertEquals(" lang=\"lang\"", HtmlAttributesFactory.lang("lang").toHtml());
    }

    public void test_id() throws IOException {
        assertEquals(" id=\"id\"", HtmlAttributesFactory.id("id").toHtml());
    }

    public void test_selected() throws IOException {
        assertEquals(" selected=\"selected\"", HtmlAttributesFactory.selected("selected").toHtml());
    }

    public void test_ismap() throws IOException {
        assertEquals(" ismap=\"ismap\"", HtmlAttributesFactory.ismap("ismap").toHtml());
    }

    public void test_style() throws IOException {
        assertEquals(" style=\"style\"", HtmlAttributesFactory.style("style").toHtml());
    }

    public void test_dir() throws IOException {
        assertEquals(" dir=\"dir\"", HtmlAttributesFactory.dir("dir").toHtml());
    }

    public void test_alt() throws IOException {
        assertEquals(" alt=\"alt\"", HtmlAttributesFactory.alt("alt").toHtml());
    }

    public void test_name() throws IOException {
        assertEquals(" name=\"name\"", HtmlAttributesFactory.name("name").toHtml());
    }

    public void test_onMouseup() throws IOException {
        assertEquals(" onmouseup=\"onmouseup\"", HtmlAttributesFactory.onMouseup("onmouseup").toHtml());
    }

    public void test_nowrap() throws IOException {
        assertEquals(" nowrap=\"nowrap\"", HtmlAttributesFactory.nowrap("nowrap").toHtml());
    }

    public void test_multiple() throws IOException {
        assertEquals(" multiple=\"multiple\"", HtmlAttributesFactory.multiple("multiple").toHtml());
    }

    public void test_classid() throws IOException {
        assertEquals(" classid=\"classid\"", HtmlAttributesFactory.classid("classid").toHtml());
    }

    public void test_profile() throws IOException {
        assertEquals(" profile=\"profile\"", HtmlAttributesFactory.profile("profile").toHtml());
    }

    public void test_axis() throws IOException {
        assertEquals(" axis=\"axis\"", HtmlAttributesFactory.axis("axis").toHtml());
    }

    public void test_onMousemove() throws IOException {
        assertEquals(" onmousemove=\"onmousemove\"", HtmlAttributesFactory.onMousemove("onmousemove").toHtml());
    }

    public void test_tabindex() throws IOException {
        assertEquals(" tabindex=\"tabindex\"", HtmlAttributesFactory.tabindex("tabindex").toHtml());
    }

    public void test_onChange() throws IOException {
        assertEquals(" onchange=\"onchange\"", HtmlAttributesFactory.onChange("onchange").toHtml());
    }

    public void test_rules() throws IOException {
        assertEquals(" rules=\"rules\"", HtmlAttributesFactory.rules("rules").toHtml());
    }

    public void test_onMouseover() throws IOException {
        assertEquals(" onmouseover=\"onmouseover\"", HtmlAttributesFactory.onMouseover("onmouseover").toHtml());
    }

    public void test_coords() throws IOException {
        assertEquals(" coords=\"coords\"", HtmlAttributesFactory.coords("coords").toHtml());
    }

    public void test_color() throws IOException {
        assertEquals(" color=\"color\"", HtmlAttributesFactory.color("color").toHtml());
    }

    public void test_onLoad() throws IOException {
        assertEquals(" onload=\"onload\"", HtmlAttributesFactory.onLoad("onload").toHtml());
    }

    public void test_target() throws IOException {
        assertEquals(" target=\"target\"", HtmlAttributesFactory.target("target").toHtml());
    }

    public void test_onClick() throws IOException {
        assertEquals(" onclick=\"onclick\"", HtmlAttributesFactory.onClick("onclick").toHtml());
    }

    public void test_valign() throws IOException {
        assertEquals(" valign=\"valign\"", HtmlAttributesFactory.valign("valign").toHtml());
    }

    public void test_disabled() throws IOException {
        assertEquals(" disabled=\"disabled\"", HtmlAttributesFactory.disabled("disabled").toHtml());
    }

    public void test_codebase() throws IOException {
        assertEquals(" codebase=\"codebase\"", HtmlAttributesFactory.codebase("codebase").toHtml());
    }
    public void test_onContextmenu() throws IOException {
        assertEquals(" oncontextmenu=\"oncontextmenu\"", HtmlAttributesFactory.onContextmenu("oncontextmenu").toHtml());
    }

    public void test_onFormchange() throws IOException {
        assertEquals(" onformchange=\"onformchange\"", HtmlAttributesFactory.onFormchange("onformchange").toHtml());
    }

    public void test_onForminput() throws IOException {
        assertEquals(" onforminput=\"onforminput\"", HtmlAttributesFactory.onForminput("onforminput").toHtml());
    }

    public void test_onInput() throws IOException {
        assertEquals(" oninput=\"oninput\"", HtmlAttributesFactory.onInput("oninput").toHtml());
    }

    public void test_onInvalid() throws IOException {
        assertEquals(" oninvalid=\"oninvalid\"", HtmlAttributesFactory.onInvalid("oninvalid").toHtml());
    }

    public void test_onCanplay() throws IOException {
        assertEquals(" oncanplay=\"oncanplay\"", HtmlAttributesFactory.onCanplay("oncanplay").toHtml());
    }

    public void test_onCanplaythrough() throws IOException {
        assertEquals(" oncanplaythrough=\"oncanplaythrough\"", HtmlAttributesFactory.onCanplaythrough("oncanplaythrough").toHtml());
    }

    public void test_onDurationchange() throws IOException {
        assertEquals(" ondurationchange=\"ondurationchange\"", HtmlAttributesFactory.onDurationchange("ondurationchange").toHtml());
    }

    public void test_onEmptied() throws IOException {
        assertEquals(" onemptied=\"onemptied\"", HtmlAttributesFactory.onEmptied("onemptied").toHtml());
    }

    public void test_onEnded() throws IOException {
        assertEquals(" onended=\"onended\"", HtmlAttributesFactory.onEnded("onended").toHtml());
    }

    public void test_onLoadeddata() throws IOException {
        assertEquals(" onloadeddata=\"onloadeddata\"", HtmlAttributesFactory.onLoadeddata("onloadeddata").toHtml());
    }

    public void test_onLoadedmetadata() throws IOException {
        assertEquals(" onloadedmetadata=\"onloadedmetadata\"", HtmlAttributesFactory.onLoadedmetadata("onloadedmetadata").toHtml());
    }

    public void test_onLoadstart() throws IOException {
        assertEquals(" onloadstart=\"onloadstart\"", HtmlAttributesFactory.onLoadstart("onloadstart").toHtml());
    }

    public void test_onPause() throws IOException {
        assertEquals(" onpause=\"onpause\"", HtmlAttributesFactory.onPause("onpause").toHtml());
    }

    public void test_onPlay() throws IOException {
        assertEquals(" onplay=\"onplay\"", HtmlAttributesFactory.onPlay("onplay").toHtml());
    }

    public void test_onPlaying() throws IOException {
        assertEquals(" onplaying=\"onplaying\"", HtmlAttributesFactory.onPlaying("onplaying").toHtml());
    }

    public void test_onProgress() throws IOException {
        assertEquals(" onprogress=\"onprogress\"", HtmlAttributesFactory.onProgress("onprogress").toHtml());
    }

    public void test_onRatechange() throws IOException {
        assertEquals(" onratechange=\"onratechange\"", HtmlAttributesFactory.onRatechange("onratechange").toHtml());
    }

    public void test_onReadystatechange() throws IOException {
        assertEquals(" onreadystatechange=\"onreadystatechange\"", HtmlAttributesFactory.onReadystatechange("onreadystatechange").toHtml());
    }

    public void test_onSeeked() throws IOException {
        assertEquals(" onseeked=\"onseeked\"", HtmlAttributesFactory.onSeeked("onseeked").toHtml());
    }

    public void test_onSeeking() throws IOException {
        assertEquals(" onseeking=\"onseeking\"", HtmlAttributesFactory.onSeeking("onseeking").toHtml());
    }

    public void test_onStalled() throws IOException {
        assertEquals(" onstalled=\"onstalled\"", HtmlAttributesFactory.onStalled("onstalled").toHtml());
    }

    public void test_onSuspend() throws IOException {
        assertEquals(" onsuspend=\"onsuspend\"", HtmlAttributesFactory.onSuspend("onsuspend").toHtml());
    }

    public void test_onTimeupdate() throws IOException {
        assertEquals(" ontimeupdate=\"ontimeupdate\"", HtmlAttributesFactory.onTimeupdate("ontimeupdate").toHtml());
    }

    public void test_onVolumechange() throws IOException {
        assertEquals(" onvolumechange=\"onvolumechange\"", HtmlAttributesFactory.onVolumechange("onvolumechange").toHtml());
    }

    public void test_onWaiting() throws IOException {
        assertEquals(" onwaiting=\"onwaiting\"", HtmlAttributesFactory.onWaiting("onwaiting").toHtml());
    }

    public void test_onDrag() throws IOException {
        assertEquals(" ondrag=\"ondrag\"", HtmlAttributesFactory.onDrag("ondrag").toHtml());
    }

    public void test_onDragend() throws IOException {
        assertEquals(" ondragend=\"ondragend\"", HtmlAttributesFactory.onDragend("ondragend").toHtml());
    }

    public void test_onDragenter() throws IOException {
        assertEquals(" ondragenter=\"ondragenter\"", HtmlAttributesFactory.onDragenter("ondragenter").toHtml());
    }

    public void test_onDragleave() throws IOException {
        assertEquals(" ondragleave=\"ondragleave\"", HtmlAttributesFactory.onDragleave("ondragleave").toHtml());
    }

    public void test_onDragover() throws IOException {
        assertEquals(" ondragover=\"ondragover\"", HtmlAttributesFactory.onDragover("ondragover").toHtml());
    }

    public void test_onDragstart() throws IOException {
        assertEquals(" ondragstart=\"ondragstart\"", HtmlAttributesFactory.onDragstart("ondragstart").toHtml());
    }

    public void test_onDrop() throws IOException {
        assertEquals(" ondrop=\"ondrop\"", HtmlAttributesFactory.onDrop("ondrop").toHtml());
    }

    public void test_onMousewheel() throws IOException {
        assertEquals(" onmousewheel=\"onmousewheel\"", HtmlAttributesFactory.onMousewheel("onmousewheel").toHtml());
    }

    public void test_onScroll() throws IOException {
        assertEquals(" onscroll=\"onscroll\"", HtmlAttributesFactory.onScroll("onscroll").toHtml());
    }

    public void test_contenteditable() throws IOException {
        assertEquals(" contenteditable=\"contenteditable\"", HtmlAttributesFactory.contenteditable("contenteditable").toHtml());
    }

    public void test_contextmenu() throws IOException {
        assertEquals(" contextmenu=\"contextmenu\"", HtmlAttributesFactory.contextmenu("contextmenu").toHtml());
    }

    public void test_draggable() throws IOException {
        assertEquals(" draggable=\"draggable\"", HtmlAttributesFactory.draggable("draggable").toHtml());
    }

    public void test_dropzone() throws IOException {
        assertEquals(" dropzone=\"dropzone\"", HtmlAttributesFactory.dropzone("dropzone").toHtml());
    }

    public void test_hidden() throws IOException {
        assertEquals(" hidden=\"hidden\"", HtmlAttributesFactory.hidden("hidden").toHtml());
    }

    public void test_spellcheck() throws IOException {
        assertEquals(" spellcheck=\"spellcheck\"", HtmlAttributesFactory.spellcheck("spellcheck").toHtml());
    }

    public void test_onAfterprint() throws IOException {
        assertEquals(" onafterprint=\"onafterprint\"", HtmlAttributesFactory.onAfterprint("onafterprint").toHtml());
    }

    public void test_onBeforeprint() throws IOException {
        assertEquals(" onbeforeprint=\"onbeforeprint\"", HtmlAttributesFactory.onBeforeprint("onbeforeprint").toHtml());
    }

    public void test_onBeforeonload() throws IOException {
        assertEquals(" onbeforeonload=\"onbeforeonload\"", HtmlAttributesFactory.onBeforeonload("onbeforeonload").toHtml());
    }

    public void test_onError() throws IOException {
        assertEquals(" onerror=\"onerror\"", HtmlAttributesFactory.onError("onerror").toHtml());
    }

    public void test_onHaschange() throws IOException {
        assertEquals(" onhaschange=\"onhaschange\"", HtmlAttributesFactory.onHaschange("onhaschange").toHtml());
    }

    public void test_onMessage() throws IOException {
        assertEquals(" onmessage=\"onmessage\"", HtmlAttributesFactory.onMessage("onmessage").toHtml());
    }

    public void test_onOffline() throws IOException {
        assertEquals(" onoffline=\"onoffline\"", HtmlAttributesFactory.onOffline("onoffline").toHtml());
    }

    public void test_onOnline() throws IOException {
        assertEquals(" ononline=\"ononline\"", HtmlAttributesFactory.onOnline("ononline").toHtml());
    }

    public void test_onPagehide() throws IOException {
        assertEquals(" onpagehide=\"onpagehide\"", HtmlAttributesFactory.onPagehide("onpagehide").toHtml());
    }

    public void test_onPageshow() throws IOException {
        assertEquals(" onpageshow=\"onpageshow\"", HtmlAttributesFactory.onPageshow("onpageshow").toHtml());
    }

    public void test_onPopstate() throws IOException {
        assertEquals(" onpopstate=\"onpopstate\"", HtmlAttributesFactory.onPopstate("onpopstate").toHtml());
    }

    public void test_onRedo() throws IOException {
        assertEquals(" onredo=\"onredo\"", HtmlAttributesFactory.onRedo("onredo").toHtml());
    }

    public void test_onResize() throws IOException {
        assertEquals(" onresize=\"onresize\"", HtmlAttributesFactory.onResize("onresize").toHtml());
    }

    public void test_onStorage() throws IOException {
        assertEquals(" onstorage=\"onstorage\"", HtmlAttributesFactory.onStorage("onstorage").toHtml());
    }

    public void test_onUndo() throws IOException {
        assertEquals(" onundo=\"onundo\"", HtmlAttributesFactory.onUndo("onundo").toHtml());
    }


}
