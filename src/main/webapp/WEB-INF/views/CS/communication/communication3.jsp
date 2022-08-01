<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="resources/css/cs/main.css" />
	<link rel="stylesheet" href="resources/css/cs/board.css" />
	<link rel="stylesheet" href="resources/css/cs/communication.css" />
    <link rel="stylesheet" href="resources/css/cs/communication2.css" />
</head>
<body>
<header>
<%@include file="../../../../resources/common/header.jsp" %>
</header>
<div class="cs_main">
<%@include file="../cs_nav/cs_left_nav.jsp" %>    
        <div class="cs_main_board">
            <div class="line" ></div>
            <h2>고객의 소리</h2>
            <div class="step_progress_area">
            	<div class= "step_progress_status">
	                <div class="step_progress">
	                    <em>01</em>
	                    <strong>개인 정보 확인</strong>
	                </div>
	                <div class="step_progress_on">
	                    <em>02</em>
	                    <strong>고객 의견 등록</strong>
	                </div>
	                <div class="step_progress">
	                    <em>03</em>
	                    <strong>접수 완료</strong>
	                </div>
               	</div>
                <P class="table_info">
                    필수입력 항목입니다.
                    <span class="point">*</span>
                </P> 
            </div>
            <table class="communicaiton_table">
                <tr>
                    <th>
                        어떤유형의 의견이신가요?
                        <span class="point">*</span>
                    </th>
                    <td>
                        <div class="cs_opinion">
                            <select>
                                <option value="배송">배송</option>
                                <option value="매장">매장</option>
                                <option value="AS">AS</option>
                                <option value="고객센터">고객센터</option>
                                <option value="상품">상품</option>
                                <option value="기타">기타</option>
                            </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>
                        어떤 구입처를 이용하셨나요?
                        <span class="point">*</span>
                    </th>
                    <td>
                        <div class="radio set">
                            <label>
                                <input type="radio" name="distrib_1_debt" value="OFF" checked="checked">
                                <span>매장</span>
                            </label> 
                            <label>
                                <input type="radio" name="distrib_1_debt" value="ON">
                                <span>온라인</span>
                            </label>     
                        </div>    
                    </td>
                </tr>
                <tr>
                    <th>
                        어떤 방법으로 답변 드리길 원하시나요?
                        <span class="point">*</span>
                    </th>
                    <td>
                        <div class="radio_set">
                            <label>
                                <input type="radio" name="distrib_1_debt" value="OFF" checked="checked">
                                <span>문자메시지</span>
                            </label> 
                            <label>
                                <input type="radio" name="distrib_1_debt" value="ON">
                                <span>이메일</span>
                            </label> 
                            <label>
                                <input type="radio" name="distrib_1_debt" value="ON">
                                <span>답변불필요</span>
                            </label>   
                            <div>
                                <p class="warning">
                                    로그인 후 [마이페이지 > 고객의 소리]에서도 답변 내용을 확인 하실 수 있습니다.
                                </p>
                            </div>   
                        </div>    
                    </td>
                </tr>
                <tr>
                    <th>
                        SMS로 답변 여부를 받으시겠어요?
                        <span class="point">*</span>
                    </th>
                    <td>
                        <div class="radio_set">
                            <label>
                                <input type="radio" name="distrib_2_debt" value="OFF" checked="checked">
                                <span>수신</span>
                            </label> 
                            <label>
                                <input type="radio" name="distrib_2_debt" value="ON">
                                <span>수신안함</span>
                            </label>  
                            <div>
                                <p class="warning">
                                    수신 체크 시 SMS로 답변여부를 안내해 드립니다.
                                </p>
                            </div>   
                        </div> 
                    </td>  
                </tr>
            </table>
            <table class="communication_table_box">
                <tr>
                    <th scope="row">제목
                    <span class="point">*</span>
                    </th>
                    <td class="communication_table_box_title">
                        <div class="communication_table_box_wrap">
                            <input type="text" name="title" class="box_input" placeholder="제목을 입력해주세요." maxlength="100">
                            <div class="byte-check">
                                <em>0</em>
                                /100
                            </div>        
                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">내용
                    <span class="point">*</span>
                    </th>
                    <td class="communication_table_box_content">
                        <div class="communication_table_box_wrap">
                            <textarea class="box_content"></textarea>     
                            <div class="byte-check2">
                                <em>0</em>
                                /1500
                            </div> 
                        </div>
                    </td>
                </tr>
                <tr>
                    <th scope="row">첨부파일</th>
                    <td>
                        <button type="button" class="btn_add">파일 첨부하기</button>
                        <li class="li_content">첨부파일은 워드, 엑셀, 파워포인트, PDF, 이미지파일(JPG,JPEG,PNG)에 한해 최대 5개까지 등록하실 수 있습니다.</li>
                        <li class="li_content">첨부파일은 파일당 최대 5MB까지 첨부 가능합니다.</li>
                    </td>
                </tr>   
            </table>
            <div class="communicaion_btn">
	        	<a href="communication4" class="cs_communication_next_button">작성완료</a>
                <a href="communication2" class="cs_communication_before_button">이전</a>
    		</div>
        </div>
</div>
<%@include file="../../../../resources/common/footer.jsp" %>
</body>
</html>