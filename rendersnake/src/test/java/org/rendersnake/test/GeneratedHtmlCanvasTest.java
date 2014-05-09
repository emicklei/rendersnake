package org.rendersnake.test;

import org.rendersnake.HtmlAttributes;
import org.rendersnake.HtmlCanvas;

import junit.framework.TestCase;

public class GeneratedHtmlCanvasTest extends TestCase {

    private HtmlCanvas html;

    public void setUp() {
        html = new HtmlCanvas();
    }

    public void test_body() throws Exception {
        assertEquals("<body></body>", html.body()._body().toHtml());
    }

    public void test_body_null() throws Exception {
        assertEquals("<body></body>", html.body(null)._body().toHtml());
    }

    public void test_body_attrs() throws Exception {
        assertEquals("<body></body>", html.body(new HtmlAttributes())._body().toHtml());
    }

    public void test_col() throws Exception {
        assertEquals("<col/>", html.col(null).toHtml());
    }

    public void test_col_null() throws Exception {
        assertEquals("<col/>", html.col(null).toHtml());
    }

    public void test_col_attrs() throws Exception {
        assertEquals("<col/>", html.col(new HtmlAttributes()).toHtml());
    }

    public void test_tr() throws Exception {
        assertEquals("<tr></tr>", html.tr()._tr().toHtml());
    }

    public void test_tr_null() throws Exception {
        assertEquals("<tr></tr>", html.tr(null)._tr().toHtml());
    }

    public void test_tr_attrs() throws Exception {
        assertEquals("<tr></tr>", html.tr(new HtmlAttributes())._tr().toHtml());
    }

    public void test_img() throws Exception {
        assertEquals("<img/>", html.img(null).toHtml());
    }

    public void test_img_null() throws Exception {
        assertEquals("<img/>", html.img(null).toHtml());
    }

    public void test_img_attrs() throws Exception {
        assertEquals("<img/>", html.img(new HtmlAttributes()).toHtml());
    }

    public void test_colgroup() throws Exception {
        assertEquals("<colgroup></colgroup>", html.colgroup()._colgroup().toHtml());
    }

    public void test_colgroup_null() throws Exception {
        assertEquals("<colgroup></colgroup>", html.colgroup(null)._colgroup().toHtml());
    }

    public void test_colgroup_attrs() throws Exception {
        assertEquals("<colgroup></colgroup>", html.colgroup(new HtmlAttributes())._colgroup().toHtml());
    }

    public void test_noscript() throws Exception {
        assertEquals("<noscript></noscript>", html.noscript()._noscript().toHtml());
    }

    public void test_noscript_null() throws Exception {
        assertEquals("<noscript></noscript>", html.noscript(null)._noscript().toHtml());
    }

    public void test_noscript_attrs() throws Exception {
        assertEquals("<noscript></noscript>", html.noscript(new HtmlAttributes())._noscript().toHtml());
    }

    public void test_td() throws Exception {
        assertEquals("<td></td>", html.td()._td().toHtml());
    }

    public void test_td_null() throws Exception {
        assertEquals("<td></td>", html.td(null)._td().toHtml());
    }

    public void test_td_attrs() throws Exception {
        assertEquals("<td></td>", html.td(new HtmlAttributes())._td().toHtml());
    }

    public void test_br() throws Exception {
        assertEquals("<br/>", html.br(null).toHtml());
    }

    public void test_br_null() throws Exception {
        assertEquals("<br/>", html.br(null).toHtml());
    }

    public void test_br_attrs() throws Exception {
        assertEquals("<br/>", html.br(new HtmlAttributes()).toHtml());
    }

    public void test_th() throws Exception {
        assertEquals("<th></th>", html.th()._th().toHtml());
    }

    public void test_th_null() throws Exception {
        assertEquals("<th></th>", html.th(null)._th().toHtml());
    }

    public void test_th_attrs() throws Exception {
        assertEquals("<th></th>", html.th(new HtmlAttributes())._th().toHtml());
    }

    public void test_kbd() throws Exception {
        assertEquals("<kbd></kbd>", html.kbd()._kbd().toHtml());
    }

    public void test_kbd_null() throws Exception {
        assertEquals("<kbd></kbd>", html.kbd(null)._kbd().toHtml());
    }

    public void test_kbd_attrs() throws Exception {
        assertEquals("<kbd></kbd>", html.kbd(new HtmlAttributes())._kbd().toHtml());
    }

    public void test_button() throws Exception {
        assertEquals("<button></button>", html.button()._button().toHtml());
    }

    public void test_button_null() throws Exception {
        assertEquals("<button></button>", html.button(null)._button().toHtml());
    }

    public void test_button_attrs() throws Exception {
        assertEquals("<button></button>", html.button(new HtmlAttributes())._button().toHtml());
    }

    public void test_h5() throws Exception {
        assertEquals("<h5></h5>", html.h5()._h5().toHtml());
    }

    public void test_h5_null() throws Exception {
        assertEquals("<h5></h5>", html.h5(null)._h5().toHtml());
    }

    public void test_h5_attrs() throws Exception {
        assertEquals("<h5></h5>", html.h5(new HtmlAttributes())._h5().toHtml());
    }

    public void test_h4() throws Exception {
        assertEquals("<h4></h4>", html.h4()._h4().toHtml());
    }

    public void test_h4_null() throws Exception {
        assertEquals("<h4></h4>", html.h4(null)._h4().toHtml());
    }

    public void test_h4_attrs() throws Exception {
        assertEquals("<h4></h4>", html.h4(new HtmlAttributes())._h4().toHtml());
    }

    public void test_samp() throws Exception {
        assertEquals("<samp></samp>", html.samp()._samp().toHtml());
    }

    public void test_samp_null() throws Exception {
        assertEquals("<samp></samp>", html.samp(null)._samp().toHtml());
    }

    public void test_samp_attrs() throws Exception {
        assertEquals("<samp></samp>", html.samp(new HtmlAttributes())._samp().toHtml());
    }

    public void test_ol() throws Exception {
        assertEquals("<ol></ol>", html.ol()._ol().toHtml());
    }

    public void test_ol_null() throws Exception {
        assertEquals("<ol></ol>", html.ol(null)._ol().toHtml());
    }

    public void test_ol_attrs() throws Exception {
        assertEquals("<ol></ol>", html.ol(new HtmlAttributes())._ol().toHtml());
    }

    public void test_h6() throws Exception {
        assertEquals("<h6></h6>", html.h6()._h6().toHtml());
    }

    public void test_h6_null() throws Exception {
        assertEquals("<h6></h6>", html.h6(null)._h6().toHtml());
    }

    public void test_h6_attrs() throws Exception {
        assertEquals("<h6></h6>", html.h6(new HtmlAttributes())._h6().toHtml());
    }

    public void test_h1() throws Exception {
        assertEquals("<h1></h1>", html.h1()._h1().toHtml());
    }

    public void test_h1_null() throws Exception {
        assertEquals("<h1></h1>", html.h1(null)._h1().toHtml());
    }

    public void test_h1_attrs() throws Exception {
        assertEquals("<h1></h1>", html.h1(new HtmlAttributes())._h1().toHtml());
    }

    public void test_head() throws Exception {
        assertEquals("<head></head>", html.head()._head().toHtml());
    }

    public void test_head_null() throws Exception {
        assertEquals("<head></head>", html.head(null)._head().toHtml());
    }

    public void test_head_attrs() throws Exception {
        assertEquals("<head></head>", html.head(new HtmlAttributes())._head().toHtml());
    }

    public void test_option() throws Exception {
        assertEquals("<option></option>", html.option()._option().toHtml());
    }

    public void test_option_null() throws Exception {
        assertEquals("<option></option>", html.option(null)._option().toHtml());
    }

    public void test_option_attrs() throws Exception {
        assertEquals("<option></option>", html.option(new HtmlAttributes())._option().toHtml());
    }

    public void test_h3() throws Exception {
        assertEquals("<h3></h3>", html.h3()._h3().toHtml());
    }

    public void test_h3_null() throws Exception {
        assertEquals("<h3></h3>", html.h3(null)._h3().toHtml());
    }

    public void test_h3_attrs() throws Exception {
        assertEquals("<h3></h3>", html.h3(new HtmlAttributes())._h3().toHtml());
    }

    public void test_h2() throws Exception {
        assertEquals("<h2></h2>", html.h2()._h2().toHtml());
    }

    public void test_h2_null() throws Exception {
        assertEquals("<h2></h2>", html.h2(null)._h2().toHtml());
    }

    public void test_h2_attrs() throws Exception {
        assertEquals("<h2></h2>", html.h2(new HtmlAttributes())._h2().toHtml());
    }

    public void test_form() throws Exception {
        assertEquals("<form></form>", html.form()._form().toHtml());
    }

    public void test_form_null() throws Exception {
        assertEquals("<form></form>", html.form(null)._form().toHtml());
    }

    public void test_form_attrs() throws Exception {
        assertEquals("<form></form>", html.form(new HtmlAttributes())._form().toHtml());
    }

    public void test_select() throws Exception {
        assertEquals("<select></select>", html.select()._select().toHtml());
    }

    public void test_select_null() throws Exception {
        assertEquals("<select></select>", html.select(null)._select().toHtml());
    }

    public void test_select_attrs() throws Exception {
        assertEquals("<select></select>", html.select(new HtmlAttributes())._select().toHtml());
    }

    public void test_ins() throws Exception {
        assertEquals("<ins></ins>", html.ins()._ins().toHtml());
    }

    public void test_ins_null() throws Exception {
        assertEquals("<ins></ins>", html.ins(null)._ins().toHtml());
    }

    public void test_ins_attrs() throws Exception {
        assertEquals("<ins></ins>", html.ins(new HtmlAttributes())._ins().toHtml());
    }

    public void test_abbr() throws Exception {
        assertEquals("<abbr></abbr>", html.abbr()._abbr().toHtml());
    }

    public void test_abbr_null() throws Exception {
        assertEquals("<abbr></abbr>", html.abbr(null)._abbr().toHtml());
    }

    public void test_abbr_attrs() throws Exception {
        assertEquals("<abbr></abbr>", html.abbr(new HtmlAttributes())._abbr().toHtml());
    }

    public void test_label() throws Exception {
        assertEquals("<label></label>", html.label()._label().toHtml());
    }

    public void test_label_null() throws Exception {
        assertEquals("<label></label>", html.label(null)._label().toHtml());
    }

    public void test_label_attrs() throws Exception {
        assertEquals("<label></label>", html.label(new HtmlAttributes())._label().toHtml());
    }

    public void test_table() throws Exception {
        assertEquals("<table></table>", html.table()._table().toHtml());
    }

    public void test_table_null() throws Exception {
        assertEquals("<table></table>", html.table(null)._table().toHtml());
    }

    public void test_table_attrs() throws Exception {
        assertEquals("<table></table>", html.table(new HtmlAttributes())._table().toHtml());
    }

    public void test_code() throws Exception {
        assertEquals("<code></code>", html.code()._code().toHtml());
    }

    public void test_code_null() throws Exception {
        assertEquals("<code></code>", html.code(null)._code().toHtml());
    }

    public void test_code_attrs() throws Exception {
        assertEquals("<code></code>", html.code(new HtmlAttributes())._code().toHtml());
    }

    public void test_script() throws Exception {
        assertEquals("<script></script>", html.script()._script().toHtml());
    }

    public void test_script_null() throws Exception {
        assertEquals("<script></script>", html.script(null)._script().toHtml());
    }

    public void test_script_attrs() throws Exception {
        assertEquals("<script></script>", html.script(new HtmlAttributes())._script().toHtml());
    }

    public void test_tfoot() throws Exception {
        assertEquals("<tfoot></tfoot>", html.tfoot()._tfoot().toHtml());
    }

    public void test_tfoot_null() throws Exception {
        assertEquals("<tfoot></tfoot>", html.tfoot(null)._tfoot().toHtml());
    }

    public void test_tfoot_attrs() throws Exception {
        assertEquals("<tfoot></tfoot>", html.tfoot(new HtmlAttributes())._tfoot().toHtml());
    }

    public void test_li() throws Exception {
        assertEquals("<li></li>", html.li()._li().toHtml());
    }

    public void test_li_null() throws Exception {
        assertEquals("<li></li>", html.li(null)._li().toHtml());
    }

    public void test_li_attrs() throws Exception {
        assertEquals("<li></li>", html.li(new HtmlAttributes())._li().toHtml());
    }

    public void test_cite() throws Exception {
        assertEquals("<cite></cite>", html.cite()._cite().toHtml());
    }

    public void test_cite_null() throws Exception {
        assertEquals("<cite></cite>", html.cite(null)._cite().toHtml());
    }

    public void test_cite_attrs() throws Exception {
        assertEquals("<cite></cite>", html.cite(new HtmlAttributes())._cite().toHtml());
    }

    public void test_input() throws Exception {
        assertEquals("<input/>", html.input(null).toHtml());
    }

    public void test_input_null() throws Exception {
        assertEquals("<input/>", html.input(null).toHtml());
    }

    public void test_input_attrs() throws Exception {
        assertEquals("<input/>", html.input(new HtmlAttributes()).toHtml());
    }

    public void test_frame() throws Exception {
        assertEquals("<frame/>", html.frame(null).toHtml());
    }

    public void test_frame_null() throws Exception {
        assertEquals("<frame/>", html.frame(null).toHtml());
    }

    public void test_frame_attrs() throws Exception {
        assertEquals("<frame/>", html.frame(new HtmlAttributes()).toHtml());
    }

    public void test_iframe() throws Exception {
        assertEquals("<iframe></iframe>", html.iframe()._iframe().toHtml());
    }

    public void test_iframe_null() throws Exception {
        assertEquals("<iframe></iframe>", html.iframe(null)._iframe().toHtml());
    }

    public void test_iframe_attrs() throws Exception {
        assertEquals("<iframe></iframe>", html.iframe(new HtmlAttributes())._iframe().toHtml());
    }

    public void test_strong() throws Exception {
        assertEquals("<strong></strong>", html.strong()._strong().toHtml());
    }

    public void test_strong_null() throws Exception {
        assertEquals("<strong></strong>", html.strong(null)._strong().toHtml());
    }

    public void test_strong_attrs() throws Exception {
        assertEquals("<strong></strong>", html.strong(new HtmlAttributes())._strong().toHtml());
    }

    public void test_textarea() throws Exception {
        assertEquals("<textarea></textarea>", html.textarea()._textarea().toHtml());
    }

    public void test_textarea_null() throws Exception {
        assertEquals("<textarea></textarea>", html.textarea(null)._textarea().toHtml());
    }

    public void test_textarea_attrs() throws Exception {
        assertEquals("<textarea></textarea>", html.textarea(new HtmlAttributes())._textarea().toHtml());
    }

    public void test_noframes() throws Exception {
        assertEquals("<noframes></noframes>", html.noframes()._noframes().toHtml());
    }

    public void test_noframes_null() throws Exception {
        assertEquals("<noframes></noframes>", html.noframes(null)._noframes().toHtml());
    }

    public void test_noframes_attrs() throws Exception {
        assertEquals("<noframes></noframes>", html.noframes(new HtmlAttributes())._noframes().toHtml());
    }

    public void test_big() throws Exception {
        assertEquals("<big></big>", html.big()._big().toHtml());
    }

    public void test_big_null() throws Exception {
        assertEquals("<big></big>", html.big(null)._big().toHtml());
    }

    public void test_big_attrs() throws Exception {
        assertEquals("<big></big>", html.big(new HtmlAttributes())._big().toHtml());
    }

    public void test_small() throws Exception {
        assertEquals("<small></small>", html.small()._small().toHtml());
    }

    public void test_small_null() throws Exception {
        assertEquals("<small></small>", html.small(null)._small().toHtml());
    }

    public void test_small_attrs() throws Exception {
        assertEquals("<small></small>", html.small(new HtmlAttributes())._small().toHtml());
    }

    public void test_span() throws Exception {
        assertEquals("<span></span>", html.span()._span().toHtml());
    }

    public void test_span_null() throws Exception {
        assertEquals("<span></span>", html.span(null)._span().toHtml());
    }

    public void test_span_attrs() throws Exception {
        assertEquals("<span></span>", html.span(new HtmlAttributes())._span().toHtml());
    }

    public void test_dt() throws Exception {
        assertEquals("<dt></dt>", html.dt()._dt().toHtml());
    }

    public void test_dt_null() throws Exception {
        assertEquals("<dt></dt>", html.dt(null)._dt().toHtml());
    }

    public void test_dt_attrs() throws Exception {
        assertEquals("<dt></dt>", html.dt(new HtmlAttributes())._dt().toHtml());
    }

    public void test_hr() throws Exception {
        assertEquals("<hr/>", html.hr(null).toHtml());
    }

    public void test_hr_null() throws Exception {
        assertEquals("<hr/>", html.hr(null).toHtml());
    }

    public void test_hr_attrs() throws Exception {
        assertEquals("<hr/>", html.hr(new HtmlAttributes()).toHtml());
    }

    public void test_sub() throws Exception {
        assertEquals("<sub></sub>", html.sub()._sub().toHtml());
    }

    public void test_sub_null() throws Exception {
        assertEquals("<sub></sub>", html.sub(null)._sub().toHtml());
    }

    public void test_sub_attrs() throws Exception {
        assertEquals("<sub></sub>", html.sub(new HtmlAttributes())._sub().toHtml());
    }

    public void test_optgroup() throws Exception {
        assertEquals("<optgroup></optgroup>", html.optgroup()._optgroup().toHtml());
    }

    public void test_optgroup_null() throws Exception {
        assertEquals("<optgroup></optgroup>", html.optgroup(null)._optgroup().toHtml());
    }

    public void test_optgroup_attrs() throws Exception {
        assertEquals("<optgroup></optgroup>", html.optgroup(new HtmlAttributes())._optgroup().toHtml());
    }

    public void test_param() throws Exception {
        assertEquals("<param/>", html.param(null).toHtml());
    }

    public void test_param_null() throws Exception {
        assertEquals("<param/>", html.param(null).toHtml());
    }

    public void test_param_attrs() throws Exception {
        assertEquals("<param/>", html.param(new HtmlAttributes()).toHtml());
    }

    public void test_bdo() throws Exception {
        assertEquals("<bdo></bdo>", html.bdo()._bdo().toHtml());
    }

    public void test_bdo_null() throws Exception {
        assertEquals("<bdo></bdo>", html.bdo(null)._bdo().toHtml());
    }

    public void test_bdo_attrs() throws Exception {
        assertEquals("<bdo></bdo>", html.bdo(new HtmlAttributes())._bdo().toHtml());
    }

    public void test_var() throws Exception {
        assertEquals("<var></var>", html.var()._var().toHtml());
    }

    public void test_var_null() throws Exception {
        assertEquals("<var></var>", html.var(null)._var().toHtml());
    }

    public void test_var_attrs() throws Exception {
        assertEquals("<var></var>", html.var(new HtmlAttributes())._var().toHtml());
    }

    public void test_link() throws Exception {
        assertEquals("<link/>", html.link(null).toHtml());
    }

    public void test_link_null() throws Exception {
        assertEquals("<link/>", html.link(null).toHtml());
    }

    public void test_link_attrs() throws Exception {
        assertEquals("<link/>", html.link(new HtmlAttributes()).toHtml());
    }

    public void test_div() throws Exception {
        assertEquals("<div></div>", html.div()._div().toHtml());
    }

    public void test_div_null() throws Exception {
        assertEquals("<div></div>", html.div(null)._div().toHtml());
    }

    public void test_div_attrs() throws Exception {
        assertEquals("<div></div>", html.div(new HtmlAttributes())._div().toHtml());
    }

    public void test_object() throws Exception {
        assertEquals("<object></object>", html.object()._object().toHtml());
    }

    public void test_object_null() throws Exception {
        assertEquals("<object></object>", html.object(null)._object().toHtml());
    }

    public void test_object_attrs() throws Exception {
        assertEquals("<object></object>", html.object(new HtmlAttributes())._object().toHtml());
    }

    public void test_sup() throws Exception {
        assertEquals("<sup></sup>", html.sup()._sup().toHtml());
    }

    public void test_sup_null() throws Exception {
        assertEquals("<sup></sup>", html.sup(null)._sup().toHtml());
    }

    public void test_sup_attrs() throws Exception {
        assertEquals("<sup></sup>", html.sup(new HtmlAttributes())._sup().toHtml());
    }

    public void test_meta() throws Exception {
        assertEquals("<meta/>", html.meta(null).toHtml());
    }

    public void test_meta_null() throws Exception {
        assertEquals("<meta/>", html.meta(null).toHtml());
    }

    public void test_meta_attrs() throws Exception {
        assertEquals("<meta/>", html.meta(new HtmlAttributes()).toHtml());
    }

    public void test_dd() throws Exception {
        assertEquals("<dd></dd>", html.dd()._dd().toHtml());
    }

    public void test_dd_null() throws Exception {
        assertEquals("<dd></dd>", html.dd(null)._dd().toHtml());
    }

    public void test_dd_attrs() throws Exception {
        assertEquals("<dd></dd>", html.dd(new HtmlAttributes())._dd().toHtml());
    }

    public void test_title() throws Exception {
        assertEquals("<title></title>", html.title()._title().toHtml());
    }

    public void test_title_null() throws Exception {
        assertEquals("<title></title>", html.title(null)._title().toHtml());
    }

    public void test_title_attrs() throws Exception {
        assertEquals("<title></title>", html.title(new HtmlAttributes())._title().toHtml());
    }

    public void test_area() throws Exception {
        assertEquals("<area/>", html.area(null).toHtml());
    }

    public void test_area_null() throws Exception {
        assertEquals("<area/>", html.area(null).toHtml());
    }

    public void test_area_attrs() throws Exception {
        assertEquals("<area/>", html.area(new HtmlAttributes()).toHtml());
    }

    public void test_style() throws Exception {
        assertEquals("<style></style>", html.style()._style().toHtml());
    }

    public void test_style_null() throws Exception {
        assertEquals("<style></style>", html.style(null)._style().toHtml());
    }

    public void test_style_attrs() throws Exception {
        assertEquals("<style></style>", html.style(new HtmlAttributes())._style().toHtml());
    }

    public void test_map() throws Exception {
        assertEquals("<map></map>", html.map()._map().toHtml());
    }

    public void test_map_null() throws Exception {
        assertEquals("<map></map>", html.map(null)._map().toHtml());
    }

    public void test_map_attrs() throws Exception {
        assertEquals("<map></map>", html.map(new HtmlAttributes())._map().toHtml());
    }

    public void test_dl() throws Exception {
        assertEquals("<dl></dl>", html.dl()._dl().toHtml());
    }

    public void test_dl_null() throws Exception {
        assertEquals("<dl></dl>", html.dl(null)._dl().toHtml());
    }

    public void test_dl_attrs() throws Exception {
        assertEquals("<dl></dl>", html.dl(new HtmlAttributes())._dl().toHtml());
    }

    public void test_del() throws Exception {
        assertEquals("<del></del>", html.del()._del().toHtml());
    }

    public void test_del_null() throws Exception {
        assertEquals("<del></del>", html.del(null)._del().toHtml());
    }

    public void test_del_attrs() throws Exception {
        assertEquals("<del></del>", html.del(new HtmlAttributes())._del().toHtml());
    }

    public void test_fieldset() throws Exception {
        assertEquals("<fieldset></fieldset>", html.fieldset()._fieldset().toHtml());
    }

    public void test_fieldset_null() throws Exception {
        assertEquals("<fieldset></fieldset>", html.fieldset(null)._fieldset().toHtml());
    }

    public void test_fieldset_attrs() throws Exception {
        assertEquals("<fieldset></fieldset>", html.fieldset(new HtmlAttributes())._fieldset().toHtml());
    }

    public void test_thead() throws Exception {
        assertEquals("<thead></thead>", html.thead()._thead().toHtml());
    }

    public void test_thead_null() throws Exception {
        assertEquals("<thead></thead>", html.thead(null)._thead().toHtml());
    }

    public void test_thead_attrs() throws Exception {
        assertEquals("<thead></thead>", html.thead(new HtmlAttributes())._thead().toHtml());
    }

    public void test_ul() throws Exception {
        assertEquals("<ul></ul>", html.ul()._ul().toHtml());
    }

    public void test_ul_null() throws Exception {
        assertEquals("<ul></ul>", html.ul(null)._ul().toHtml());
    }

    public void test_ul_attrs() throws Exception {
        assertEquals("<ul></ul>", html.ul(new HtmlAttributes())._ul().toHtml());
    }

    public void test_acronym() throws Exception {
        assertEquals("<acronym></acronym>", html.acronym()._acronym().toHtml());
    }

    public void test_acronym_null() throws Exception {
        assertEquals("<acronym></acronym>", html.acronym(null)._acronym().toHtml());
    }

    public void test_acronym_attrs() throws Exception {
        assertEquals("<acronym></acronym>", html.acronym(new HtmlAttributes())._acronym().toHtml());
    }

    public void test_b() throws Exception {
        assertEquals("<b></b>", html.b()._b().toHtml());
    }

    public void test_b_null() throws Exception {
        assertEquals("<b></b>", html.b(null)._b().toHtml());
    }

    public void test_b_attrs() throws Exception {
        assertEquals("<b></b>", html.b(new HtmlAttributes())._b().toHtml());
    }

    public void test_a() throws Exception {
        assertEquals("<a></a>", html.a()._a().toHtml());
    }

    public void test_a_null() throws Exception {
        assertEquals("<a></a>", html.a(null)._a().toHtml());
    }

    public void test_a_attrs() throws Exception {
        assertEquals("<a></a>", html.a(new HtmlAttributes())._a().toHtml());
    }

    public void test_blockquote() throws Exception {
        assertEquals("<blockquote></blockquote>", html.blockquote()._blockquote().toHtml());
    }

    public void test_blockquote_null() throws Exception {
        assertEquals("<blockquote></blockquote>", html.blockquote(null)._blockquote().toHtml());
    }

    public void test_blockquote_attrs() throws Exception {
        assertEquals("<blockquote></blockquote>", html.blockquote(new HtmlAttributes())._blockquote().toHtml());
    }

    public void test_caption() throws Exception {
        assertEquals("<caption></caption>", html.caption()._caption().toHtml());
    }

    public void test_caption_null() throws Exception {
        assertEquals("<caption></caption>", html.caption(null)._caption().toHtml());
    }

    public void test_caption_attrs() throws Exception {
        assertEquals("<caption></caption>", html.caption(new HtmlAttributes())._caption().toHtml());
    }

    public void test_i() throws Exception {
        assertEquals("<i></i>", html.i()._i().toHtml());
    }

    public void test_i_null() throws Exception {
        assertEquals("<i></i>", html.i(null)._i().toHtml());
    }

    public void test_i_attrs() throws Exception {
        assertEquals("<i></i>", html.i(new HtmlAttributes())._i().toHtml());
    }

    public void test_tbody() throws Exception {
        assertEquals("<tbody></tbody>", html.tbody()._tbody().toHtml());
    }

    public void test_tbody_null() throws Exception {
        assertEquals("<tbody></tbody>", html.tbody(null)._tbody().toHtml());
    }

    public void test_tbody_attrs() throws Exception {
        assertEquals("<tbody></tbody>", html.tbody(new HtmlAttributes())._tbody().toHtml());
    }

    public void test_frameset() throws Exception {
        assertEquals("<frameset></frameset>", html.frameset()._frameset().toHtml());
    }

    public void test_frameset_null() throws Exception {
        assertEquals("<frameset></frameset>", html.frameset(null)._frameset().toHtml());
    }

    public void test_frameset_attrs() throws Exception {
        assertEquals("<frameset></frameset>", html.frameset(new HtmlAttributes())._frameset().toHtml());
    }

    public void test_tt() throws Exception {
        assertEquals("<tt></tt>", html.tt()._tt().toHtml());
    }

    public void test_tt_null() throws Exception {
        assertEquals("<tt></tt>", html.tt(null)._tt().toHtml());
    }

    public void test_tt_attrs() throws Exception {
        assertEquals("<tt></tt>", html.tt(new HtmlAttributes())._tt().toHtml());
    }

    public void test_address() throws Exception {
        assertEquals("<address></address>", html.address()._address().toHtml());
    }

    public void test_address_null() throws Exception {
        assertEquals("<address></address>", html.address(null)._address().toHtml());
    }

    public void test_address_attrs() throws Exception {
        assertEquals("<address></address>", html.address(new HtmlAttributes())._address().toHtml());
    }

    public void test_q() throws Exception {
        assertEquals("<q></q>", html.q()._q().toHtml());
    }

    public void test_q_null() throws Exception {
        assertEquals("<q></q>", html.q(null)._q().toHtml());
    }

    public void test_q_attrs() throws Exception {
        assertEquals("<q></q>", html.q(new HtmlAttributes())._q().toHtml());
    }

    public void test_pre() throws Exception {
        assertEquals("<pre></pre>", html.pre()._pre().toHtml());
    }

    public void test_pre_null() throws Exception {
        assertEquals("<pre></pre>", html.pre(null)._pre().toHtml());
    }

    public void test_pre_attrs() throws Exception {
        assertEquals("<pre></pre>", html.pre(new HtmlAttributes())._pre().toHtml());
    }

    public void test_legend() throws Exception {
        assertEquals("<legend></legend>", html.legend()._legend().toHtml());
    }

    public void test_legend_null() throws Exception {
        assertEquals("<legend></legend>", html.legend(null)._legend().toHtml());
    }

    public void test_legend_attrs() throws Exception {
        assertEquals("<legend></legend>", html.legend(new HtmlAttributes())._legend().toHtml());
    }

    public void test_p() throws Exception {
        assertEquals("<p></p>", html.p()._p().toHtml());
    }

    public void test_p_null() throws Exception {
        assertEquals("<p></p>", html.p(null)._p().toHtml());
    }

    public void test_p_attrs() throws Exception {
        assertEquals("<p></p>", html.p(new HtmlAttributes())._p().toHtml());
    }

    public void test_base() throws Exception {
        assertEquals("<base/>", html.base(null).toHtml());
    }

    public void test_base_null() throws Exception {
        assertEquals("<base/>", html.base(null).toHtml());
    }

    public void test_base_attrs() throws Exception {
        assertEquals("<base/>", html.base(new HtmlAttributes()).toHtml());
    }

    public void test_html() throws Exception {
        assertEquals("<html></html>", html.html()._html().toHtml());
    }

    public void test_html_null() throws Exception {
        assertEquals("<html></html>", html.html(null)._html().toHtml());
    }

    public void test_html_attrs() throws Exception {
        assertEquals("<html></html>", html.html(new HtmlAttributes())._html().toHtml());
    }

    public void test_em() throws Exception {
        assertEquals("<em></em>", html.em()._em().toHtml());
    }

    public void test_em_null() throws Exception {
        assertEquals("<em></em>", html.em(null)._em().toHtml());
    }

    public void test_em_attrs() throws Exception {
        assertEquals("<em></em>", html.em(new HtmlAttributes())._em().toHtml());
    }

    public void test_dfn() throws Exception {
        assertEquals("<dfn></dfn>", html.dfn()._dfn().toHtml());
    }

    public void test_dfn_null() throws Exception {
        assertEquals("<dfn></dfn>", html.dfn(null)._dfn().toHtml());
    }

    public void test_dfn_attrs() throws Exception {
        assertEquals("<dfn></dfn>", html.dfn(new HtmlAttributes())._dfn().toHtml());
    }
    public void test_article() throws Exception {
        assertEquals("<article></article>", html.article()._article().toHtml());
    }
    public void test_article_null() throws Exception {
        assertEquals("<article></article>", html.article(null)._article().toHtml());
    }
    public void test_article_attrs() throws Exception {
        assertEquals("<article></article>", html.article(new HtmlAttributes())._article().toHtml());
    }
    public void test_aside() throws Exception {
        assertEquals("<aside></aside>", html.aside()._aside().toHtml());
    }
    public void test_aside_null() throws Exception {
        assertEquals("<aside></aside>", html.aside(null)._aside().toHtml());
    }
    public void test_aside_attrs() throws Exception {
        assertEquals("<aside></aside>", html.aside(new HtmlAttributes())._aside().toHtml());
    }
    public void test_audio() throws Exception {
        assertEquals("<audio></audio>", html.audio()._audio().toHtml());
    }
    public void test_audio_null() throws Exception {
        assertEquals("<audio></audio>", html.audio(null)._audio().toHtml());
    }
    public void test_audio_attrs() throws Exception {
        assertEquals("<audio></audio>", html.audio(new HtmlAttributes())._audio().toHtml());
    }
    public void test_canvas() throws Exception {
        assertEquals("<canvas></canvas>", html.canvas()._canvas().toHtml());
    }
    public void test_canvas_null() throws Exception {
        assertEquals("<canvas></canvas>", html.canvas(null)._canvas().toHtml());
    }
    public void test_canvas_attrs() throws Exception {
        assertEquals("<canvas></canvas>", html.canvas(new HtmlAttributes())._canvas().toHtml());
    }
    public void test_command() throws Exception {
        assertEquals("<command></command>", html.command()._command().toHtml());
    }
    public void test_command_null() throws Exception {
        assertEquals("<command></command>", html.command(null)._command().toHtml());
    }
    public void test_command_attrs() throws Exception {
        assertEquals("<command></command>", html.command(new HtmlAttributes())._command().toHtml());
    }
    public void test_datalist() throws Exception {
        assertEquals("<datalist></datalist>", html.datalist()._datalist().toHtml());
    }
    public void test_datalist_null() throws Exception {
        assertEquals("<datalist></datalist>", html.datalist(null)._datalist().toHtml());
    }
    public void test_datalist_attrs() throws Exception {
        assertEquals("<datalist></datalist>", html.datalist(new HtmlAttributes())._datalist().toHtml());
    }
    public void test_details() throws Exception {
        assertEquals("<details></details>", html.details()._details().toHtml());
    }
    public void test_details_null() throws Exception {
        assertEquals("<details></details>", html.details(null)._details().toHtml());
    }
    public void test_details_attrs() throws Exception {
        assertEquals("<details></details>", html.details(new HtmlAttributes())._details().toHtml());
    }
    public void test_embed() throws Exception {
        assertEquals("<embed></embed>", html.embed()._embed().toHtml());
    }
    public void test_embed_null() throws Exception {
        assertEquals("<embed></embed>", html.embed(null)._embed().toHtml());
    }
    public void test_embed_attrs() throws Exception {
        assertEquals("<embed></embed>", html.embed(new HtmlAttributes())._embed().toHtml());
    }
    public void test_figcaption() throws Exception {
        assertEquals("<figcaption></figcaption>", html.figcaption()._figcaption().toHtml());
    }
    public void test_figcaption_null() throws Exception {
        assertEquals("<figcaption></figcaption>", html.figcaption(null)._figcaption().toHtml());
    }
    public void test_figcaption_attrs() throws Exception {
        assertEquals("<figcaption></figcaption>", html.figcaption(new HtmlAttributes())._figcaption().toHtml());
    }
    public void test_figure() throws Exception {
        assertEquals("<figure></figure>", html.figure()._figure().toHtml());
    }
    public void test_figure_null() throws Exception {
        assertEquals("<figure></figure>", html.figure(null)._figure().toHtml());
    }
    public void test_figure_attrs() throws Exception {
        assertEquals("<figure></figure>", html.figure(new HtmlAttributes())._figure().toHtml());
    }
    public void test_footer() throws Exception {
        assertEquals("<footer></footer>", html.footer()._footer().toHtml());
    }
    public void test_footer_null() throws Exception {
        assertEquals("<footer></footer>", html.footer(null)._footer().toHtml());
    }
    public void test_footer_attrs() throws Exception {
        assertEquals("<footer></footer>", html.footer(new HtmlAttributes())._footer().toHtml());
    }
    public void test_header() throws Exception {
        assertEquals("<header></header>", html.header()._header().toHtml());
    }
    public void test_header_null() throws Exception {
        assertEquals("<header></header>", html.header(null)._header().toHtml());
    }
    public void test_header_attrs() throws Exception {
        assertEquals("<header></header>", html.header(new HtmlAttributes())._header().toHtml());
    }
    public void test_hgroup() throws Exception {
        assertEquals("<hgroup></hgroup>", html.hgroup()._hgroup().toHtml());
    }
    public void test_hgroup_null() throws Exception {
        assertEquals("<hgroup></hgroup>", html.hgroup(null)._hgroup().toHtml());
    }
    public void test_hgroup_attrs() throws Exception {
        assertEquals("<hgroup></hgroup>", html.hgroup(new HtmlAttributes())._hgroup().toHtml());
    }
    public void test_keygen() throws Exception {
        assertEquals("<keygen></keygen>", html.keygen()._keygen().toHtml());
    }
    public void test_keygen_null() throws Exception {
        assertEquals("<keygen></keygen>", html.keygen(null)._keygen().toHtml());
    }
    public void test_keygen_attrs() throws Exception {
        assertEquals("<keygen></keygen>", html.keygen(new HtmlAttributes())._keygen().toHtml());
    }
    public void test_mark() throws Exception {
        assertEquals("<mark></mark>", html.mark()._mark().toHtml());
    }
    public void test_mark_null() throws Exception {
        assertEquals("<mark></mark>", html.mark(null)._mark().toHtml());
    }
    public void test_mark_attrs() throws Exception {
        assertEquals("<mark></mark>", html.mark(new HtmlAttributes())._mark().toHtml());
    }
    public void test_meter() throws Exception {
        assertEquals("<meter></meter>", html.meter()._meter().toHtml());
    }
    public void test_meter_null() throws Exception {
        assertEquals("<meter></meter>", html.meter(null)._meter().toHtml());
    }
    public void test_meter_attrs() throws Exception {
        assertEquals("<meter></meter>", html.meter(new HtmlAttributes())._meter().toHtml());
    }
    public void test_nav() throws Exception {
        assertEquals("<nav></nav>", html.nav()._nav().toHtml());
    }
    public void test_nav_null() throws Exception {
        assertEquals("<nav></nav>", html.nav(null)._nav().toHtml());
    }
    public void test_nav_attrs() throws Exception {
        assertEquals("<nav></nav>", html.nav(new HtmlAttributes())._nav().toHtml());
    }
    public void test_output() throws Exception {
        assertEquals("<output></output>", html.output()._output().toHtml());
    }
    public void test_output_null() throws Exception {
        assertEquals("<output></output>", html.output(null)._output().toHtml());
    }
    public void test_output_attrs() throws Exception {
        assertEquals("<output></output>", html.output(new HtmlAttributes())._output().toHtml());
    }
    public void test_progress() throws Exception {
        assertEquals("<progress></progress>", html.progress()._progress().toHtml());
    }
    public void test_progress_null() throws Exception {
        assertEquals("<progress></progress>", html.progress(null)._progress().toHtml());
    }
    public void test_progress_attrs() throws Exception {
        assertEquals("<progress></progress>", html.progress(new HtmlAttributes())._progress().toHtml());
    }
    public void test_rp() throws Exception {
        assertEquals("<rp></rp>", html.rp()._rp().toHtml());
    }
    public void test_rp_null() throws Exception {
        assertEquals("<rp></rp>", html.rp(null)._rp().toHtml());
    }
    public void test_rp_attrs() throws Exception {
        assertEquals("<rp></rp>", html.rp(new HtmlAttributes())._rp().toHtml());
    }
    public void test_rt() throws Exception {
        assertEquals("<rt></rt>", html.rt()._rt().toHtml());
    }
    public void test_rt_null() throws Exception {
        assertEquals("<rt></rt>", html.rt(null)._rt().toHtml());
    }
    public void test_rt_attrs() throws Exception {
        assertEquals("<rt></rt>", html.rt(new HtmlAttributes())._rt().toHtml());
    }
    public void test_ruby() throws Exception {
        assertEquals("<ruby></ruby>", html.ruby()._ruby().toHtml());
    }
    public void test_ruby_null() throws Exception {
        assertEquals("<ruby></ruby>", html.ruby(null)._ruby().toHtml());
    }
    public void test_ruby_attrs() throws Exception {
        assertEquals("<ruby></ruby>", html.ruby(new HtmlAttributes())._ruby().toHtml());
    }
    public void test_section() throws Exception {
        assertEquals("<section></section>", html.section()._section().toHtml());
    }
    public void test_section_null() throws Exception {
        assertEquals("<section></section>", html.section(null)._section().toHtml());
    }
    public void test_section_attrs() throws Exception {
        assertEquals("<section></section>", html.section(new HtmlAttributes())._section().toHtml());
    }
    public void test_source() throws Exception {
        assertEquals("<source></source>", html.source()._source().toHtml());
    }
    public void test_source_null() throws Exception {
        assertEquals("<source></source>", html.source(null)._source().toHtml());
    }
    public void test_source_attrs() throws Exception {
        assertEquals("<source></source>", html.source(new HtmlAttributes())._source().toHtml());
    }
    public void test_summary() throws Exception {
        assertEquals("<summary></summary>", html.summary()._summary().toHtml());
    }
    public void test_summary_null() throws Exception {
        assertEquals("<summary></summary>", html.summary(null)._summary().toHtml());
    }
    public void test_summary_attrs() throws Exception {
        assertEquals("<summary></summary>", html.summary(new HtmlAttributes())._summary().toHtml());
    }
    public void test_time() throws Exception {
        assertEquals("<time></time>", html.time()._time().toHtml());
    }
    public void test_time_null() throws Exception {
        assertEquals("<time></time>", html.time(null)._time().toHtml());
    }
    public void test_time_attrs() throws Exception {
        assertEquals("<time></time>", html.time(new HtmlAttributes())._time().toHtml());
    }
    public void test_video() throws Exception {
        assertEquals("<video></video>", html.video()._video().toHtml());
    }
    public void test_video_null() throws Exception {
        assertEquals("<video></video>", html.video(null)._video().toHtml());
    }
    public void test_video_attrs() throws Exception {
        assertEquals("<video></video>", html.video(new HtmlAttributes())._video().toHtml());
    }
    public void test_wbr() throws Exception {
        assertEquals("<wbr></wbr>", html.wbr()._wbr().toHtml());
    }
    public void test_wbr_null() throws Exception {
        assertEquals("<wbr></wbr>", html.wbr(null)._wbr().toHtml());
    }
    public void test_wbr_attrs() throws Exception {
        assertEquals("<wbr></wbr>", html.wbr(new HtmlAttributes())._wbr().toHtml());
    }
}