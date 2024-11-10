# 🚀 프리코스 4주차 미션 - 편의점

---

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한 저장소입니다.

<br>

## 📃 구현 리스트

---

### 기능 요구 사항 리스트

- [x] 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
  - [x] 총 구매액은 `상품별 가격 * 수량`으로 계산한다.
  - [x] 프로모션 및 멤버십 할인 정책을 반영해 최종 결제 금액을 산출한다.
- [x] 구매 내역과 산출한 금액 정보를 영수증으로 출력한다.
- [x] 영수증 출력 후 추가 구매를 진행할지 또는 종료할지를 선택할 수 있다.
- [x] 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시킨다.
  - [x] `[ERROR]`로 시작하는 에러 문구를 출력한다.
  - [x] 에러가 발생한 부분부터 입력을 다시 받는다.

- 재고 관리
  - [x] 각 상품의 재고 수량을 고려하여 결제 가능 여부를 확인한다.
  - [x] 고객이 상품을 구매할 때마다, 결제된 수량만큼 해당 상품의 재고에서 차감하여 수량을 관리한다.
  - [x] 재고를 차감함으로써 시스템은 최신 재고 상태를 유지한다.
  - [x] 다음 고객이 구매할 때 정확한 재고 정보를 제공한다.

- 프로모션 할인
  - [x] 오늘 날짜가 프로모션 기간 내 포함된 경우에만 할인을 적용한다.
  - [x] 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free)의 형태로 진행된다.
  - [x] 1+1 또는 2+1 프로모션이 각각 지정된 상품에만 적용되며, 동일 상품에 여러 프로모션이 적용되지 않는다.
  - [x] 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.
  - [x] 프로모션 기간 중이라면 프로모션 재고를 우선적으로 차감하며, 프로모션 재고가 부족할 경우에는 일반 재고를 사용한다.
  - [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
  - [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제하게 됨을 안내한다.

- 멤버십 할인
  - [x] 멤버십 할인은 프로모션 미적용 금액의 30%를 할인받는다.
  - [x] 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
  - [x] 멤버십 할인의 최대 한도는 `8,000원`이다.

- 영수증 출력
  - [x] 영수증은 고객의 구매 내역과 할인을 요약하여 출력한다.
  - [x] 영수증 항목 상세 내역
    - [x] 구매 상품 내역 : 구매한 상품명, 수량, 가격
    - [x] 증정 상품 내역 : 프로모션에 따라 무료로 제공된 증정 상품의 목록
    - [x] 금액 정보
      - [x] 총 구매액 : 구매한 상품의 총 수량과 총 금액
      - [x] 행사 할인 : 프로모션에 의해 할인된 금액
      - [x] 멤버십 할인 : 멤버십에 의해 추가로 할인된 금액
      - [x] 내실 돈 : 최종 결제 금액
    - [x] 영수증의 구성 요소를 보기 좋게 정렬하여 고객이 쉽게 금액과 수량을 확인할 수 있게 한다.

### 기능 구현 리스트

- `[상품명-수량]`에서 상품명과 수량 분리
  - [x] 구분자(쉼표 `,`)를 기준으로 개별 상품을 분리한다.
  - [x] 개별 상품의 처음과 끝이 `[]`인지 확인한다.
    - [x] `[]` 형태가 아니라면 예외를 발생시킨다.
    - [x] `[]` 형태라면 대괄호 `[]`를 제거한다.
  - [x] 구분자(하이픈 `-`)를 기준으로 상품명과 수량을 분리한다.
    - [x] 구분자를 포함하지 않을 경우 예외를 발생시킨다.
- 상품명 검증
  - [x] 상품 목록에 상품명이 존재하지 않을 경우 예외를 발생시킨다.
- 수량 검증
  - [x] 수량이 정수가 아닐 경우 예외를 발생시킨다.
  - [x] 수량이 양수가 아닐 경우 예외를 발생시킨다.
- 프로모션 적용 가능 여부 판단
  - [x] 상품 목록에서 상품명과 일치하면서 `promotion != null`인 `프로모션 중인 상품`을 가져온다.
    - [x] `프로모션 중인 상품 == null`라면 `promotion` 적용이 불가능하다고 판단한다.
    - [x] `프로모션 중인 사움 != null`라면 `promotion`의 날짜를 가져와 현재 날짜와 비교한다.
      - [x] `promotion`의 날짜가 현재 날짜를 포함한다면 `promotion`을 적용 가능하다고 판단한다.
      - [x] `promotion`의 날짜가 현재 날짜를 포함하지 않는다면 `promotion` 적용이 불가능하다고 판단한다.
- 프로모션 적용
  - [x] 프로모션 상품에서 재고 수량을 가져온다.
    - [x] `재고 수량 >= 구입할 수량`일 경우 프로모션을 적용한다.
      - [x] `구입할 수 있는 상품 수량 = 구입할 수량 / (buy + get) * buy`
      - [x] `받을 수 있는 증정 수량 = 구입할 수량 / (buy + get) * get`
      - [x] `나머지 수량 = 구입할 수량 - 구입할 수 있는 수량 - 받을 수 있는 증정 수량`
      - [x] `재고 수량 = 재고 수량 - 구입할 수 있는 상품 수량 - 받을 수 있는 증정 수량 - 나머지 수량`
      - [x] 프로모션 적용 가능 수량보다 적게 가져온 경우, 사용자로부터 추가 여부 입력받는다.
        - [x] `buy == 나머지 수량이고 재고 수량 >= get`인 경우만 가능하도록 한다.
          - [x] `Y`인 경우 증정 받을 수 있는 상품을 추가한다.
            - [x] `구입한 상품 수량 = 구입할 수 있는 상품 수량 + 나머지 수량`
            - [x] `받은 증정 수량 = 받을 수 있는 수량 + get`
            - [x] `재고 수량 = 재고 수량 - get`
          - [x] `N`인 경우 증정 받을 수 있는 상품을 추가하지 않는다.
            - [x] `구입한 상품 수량 = 구입할 수 있는 상품 수량 + 나머지 수량`
            - [x] `받은 증정 수량 = 받을 수 있는 증정 수량`
        - [x] `buy != 나머지 수량`일 경우, 프로모션 적용을 종료한다.
    - [x] `재고 수량 < 구입할 수량`일 경우 재고 수량 내에서 구입할 수 있는 상품 수량와 증정 상품 수량을 계산한다.
      - [x] `구입할 수 있는 상품 수량 = 재고 수량 / (buy + get) * buy`
      - [x] `받을 수 있는 증정 수량 = 재고 수량 / (buy + get) * get`
      - [x] `나머지 수량 = 구입할 수량 - 구입할 수 있는 수량 - 받을 수 있는 증정 수량`
      - [x] `구입할 수 없는 상품 수량 = 구입할 수량 - 재고 수량`
      - [x] `재고 수량 = 0`으로 갱신한다.
      - [x] 프로모션 혜택 없이 결제해야 하는 경우, 사용자로부터 결제 여부를 입력받는다.
        - [x] `정가 재고 수량 >= 구입할 수 없는 사움 수량`인 경우만 동작하도록 한다. 
        - [x] `Y`인 경우 구입할 수 없는 상품 수량만큼 정가 상품에서 결제한다.
          - [x] `구입한 상품 수량 = 구입할 수 있는 상품 수량 + 구입할 수 없는 상품 수량`
          - [x] `정가 재고 수량 = 정가 ㅈ고 수량 - 구입할 수 없는 상품 수량`으로 갱신한다.
          - [x] 구입한 상품 수량만큼 지불 금액을 갱신한다. `지불 금액 += 정가 상품 수량 * 정가 상품 가격`
        - [x] `N`인 경우 구입할 수 없는 상품 수량을 제외한 후 결제를 진행한다.
    - [x] 지불 금액과 행사 할인 금액을 계산한다.
      - [x] `지불 금액 += 구입한 상품 수량 * 상품 가격`
      - [x] `할인 금액 = 받은 증정 수량 * 상품 가격`
  - [x] 상품 구입 리스트에 구입 상품 추가 
    - [x] `{상품명} {수량} {금액}` 형태로 구입 상품 리스트에 추가한다.
  - [x] 증정 리스트에 증정 상품 추가
    - [x] `{증정 상품명} {증정 개수}` 형태로 증정 상품 리스트에 추가한다.
- 프로모션 미적용
  - [x] `promotion == null`인 정가 상품을 가져와 계산한다.
    - [x] `재고 수량 < 구입할 수량`일 경우 예외를 발생시킨다.
    - [x] `재고 수량 >= 구입할 수량`일 경우 구입 금액 등을 계산한다.
      - [x] `지불 금액 = 구입할 수량 * 상품 가격`
      - [x] `할인 금액 = 0`
      - [x] `재고 = 재고 - 구입할 수량`
      - [x] 상품 구입 리스트에 구입 상품 추가
        - [x] `{상품명} {수량} {금액}` 형태로 구입 상품 리스트에 추가한다.
- 멤버십 할인 적용
  - [x] 멤버십 할인 적용 여부를 입력받는다.
    - [x] `Y`인 경우 멤버십 할인을 적용한다.
      - [x] 프로모션 미적용 금액의 30%를 할인한다.
      - [x] 단, 멤버십 할인의 최대 한도는 `8,000원`으로 제한한다.
    - [x] `N`인 경우 멤버십 할인을 적용하지 않는다.
- 영수증 발행 
  - [x] 상품 구입 리스트 출력
  - [x] 증정 리스트 출력
  - [x] `총 구매액 = 지불 금액들의 합`
  - [x] `행사 할인 금액 = 할인 금액들의 합`
    - [x] 앞에 `-`를 붙여 출력한다.
  - [x] `멤버십 할인 = 멤버십 할인
    - [x] 앞에 `-`를 붙여 출력한다.
  - [x] `내실돈 = 총 구매액 - 행사 할인 금액 - 멤버십 할인`
- 추가 구매 여부 입력
  - [x] 추가 구매 여부를 입력받는다.
    - [x] `Y`인 경우 재고가 업데이트된 사움 목록을 확인 후 추가 구매를 진행한다.
    - [x] `N`인 경우 구매를 종료한다.

### 입출력 구현 리스트

- 입력
  - [x] 상품 목록과 행사 목록을 파입 입출력을 통해 불러온다.
  - [x] 구매할 상품과 수량을 입력받는다.
    - [x] `[상품명-수량]`의 형식에서 벗어날 경우 예외를 발생시킨다.
  - [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져온 경우, 그 수량만큼 추가 여부를 입력받는다.
    - [x] 추가 여부가 `Y` 또는 `N`이 아닐 경우 예외를 발생시킨다.
  - [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할지 여부를 입력받는다.
    - [x] 결제 여부가 `Y` 또는 `N`이 아닐 경우 예외를 발생시킨다.
  - [x] 멤버십 할인 적용 여부를 입력받는다.
      - [x] 멤버십 할인 적용 여부가 `Y` 또는 `N`이 아닐 경우 예외를 발생시킨다.
  - [x] 추가 구매 여부를 입력받는다.
    - [x] 추가 구매 여부가 `Y` 또는 `N`이 아닐 경우 예외를 발생시킨다.

- 출력
  - [x] 환영 인사 문구를 출력한다. `안녕하세요. W편의점입니다.`
  - [x] 현재 보유 중인 상품을 알려주는 문구를 출력한다. `현재 보유하고 있는 상품입니다.`
  - [x] 현재 보유 중인 상품, 가격, 재고, 프로모션을 출력한다. `- {상품명} {가격} {재고} {프로모션}`
    - [x] 재고가 0개라면 `재고 없음`을 출력한다.
    - [x] 프로모션이 존재하지 않는다면 출력하지 않는다.
  - [x] 상품과 수량 입력 문구를 출력한다. `구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])`
  - [x] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량만큼 가져오지 않았을 경우, 혜택에 대한 안내 문구를 출력한다. `현재 {상품명}은(는) {get}개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)`
  - [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 일부 수량에 대해 정가로 결제할 지 여부에 대한 안내 문구를 출력한다. 
    - `현재 {상품명} {수량}개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)`
  - [x] 멤버십 할인 적용 여부를 확인하기 위한 안내 문구를 출력한다. `멤버십 할인을 받으시겠습니까? (Y/N)`
  - [x] 구매 상품 내역, 증정 상품 내역, 금액 정보를 출력한다. 
  - ```
    ===========W 편의점=============
    상품명		수량	금액
    콜라		3 	3,000
    에너지바 	5 	10,000
    ===========증	정=============
    콜라		1
    ==============================
    총구매액		8	13,000
    행사할인			-1,000
    멤버십할인		-3,000
    내실돈			 9,000
    ```
  - [x] 추가 구매 여부 확인 문구를 출력한다. `감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)`
  - [x] 사용자가 잘못된 값을 입력했을 때, `[ERROR]`로 시작하는 오류 메시지와 함께 상황에 맞는 안내 문구를 출력한다.
    - [x] 구매할 상품과 수량 형식이 올바르지 않은 경우 `[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.`
    - [x] 존재하지 않는 상품을 입력한 경우 `[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.`
    - [x] 구매 수량이 재고 수량을 초과한 경우 `[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.`
    - [x] 기타 잘못된 입력의 경우 `[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.`