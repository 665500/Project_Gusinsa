<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="resources/css/cs/enter.css" />
<link rel="stylesheet" href="resources/css/cs/main.css" />
</head>
<script>
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}
</script>
<body>
<%@include file="../../../../resources/common/header.jsp" %>
<div class="cs_main">
<%@include file="../cs_nav/cs_left_nav.jsp" %> 
	<div class="cs_main_board">
        <div>
            <h2>입점문의 페이지</h2>
        </div>

        <div>
            <div class="circle">
                <p>1</p>
            </div>
        </div>


        <div class="enter_form">
            <div class="enter_title">
                <h2>기본정보</h2>
                <p><span class="point">*</span>&nbsp; 는 필수 입력 항목입니다.</p>
            </div>
            <table class="enter_table"> 
                <tr>
                    <th>
                        사업자 등록번호 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        상호명 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th rowspan="2">
                        주소 <span class="point">*</span>
                    </th>
                    <td>
	                    <input type="text" id="sample6_postcode" placeholder="우편번호" readonly="readonly" class="postcode">
	                    <input type="text" name="" id="sample6_address" placeholder="주소" readonly="readonly" class="addr">
	                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="addr_btn">
                    </td>
                </tr>
                <tr>
                    <td>
                    	<input type="text" name="" id="sample6_extraAddress" placeholder="참고항목" readonly="readonly" >
                    	<input type="text" name="" id="sample6_detailAddress" placeholder=" 상세 주소를 입력해주세요.">
                    </td>
                </tr>
                <tr>
                    <th>
                        연매출 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
            </table>
        </div>

        <div class="enter_form">
            <div class="enter_title">
                <h2>파트너 신청정보</h2>
            </div>
            <table class="enter_table"> 
                <tr>
                    <th>
                        브랜드명 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        상품소개 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>

                <tr>
                    <th>
                        홈페이지 주소 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
            </table>
        </div>

        <div class="enter_form">
            <div class="enter_title">
                <h2>영업 담당자 정보</h2>
            </div>
            <table class="enter_table"> 
                <tr>
                    <th>
                        담당자명 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        직급 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        전화번호 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        팩스번호 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        휴대폰번호 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
                <tr>
                    <th>
                        이메일 <span class="point">*</span>
                    </th>
                    <td><input type="text" class="input_style"></td>
                </tr>
            </table>

           
        </div>
        <div class="enter_btn">
            <input type="submit" value="작성완료">
        </div>   
	</div>
</div>	
</body>
</html>