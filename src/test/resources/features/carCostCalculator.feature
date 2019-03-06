Feature: Car cost calculator

  As a consumer
  I want to get cost of car with options added
  So that I can make decisions on what options to add

  Scenario Outline: Get the total cost of the car with options added
    Given that user wants to know the cost of the car
    When a request is made to for <carType> with <options> and <destinationZip>
    Then the <carCost> of the car is returned

    Examples:
      | carType      | options                                                 | destinationZip | carCost |
      | coupe        | v8,automatic,premiumaudio,sunroof,navigation            | 12345          | 24000   |
      | truck        | v8,automatic,premiumaudio,sunroof,navigation,towpackage | 12345          | 38500   |
      | suv          | v8,automatic,premiumaudio,sunroof,navigation,towpackage | 12345          | 43050   |
      | luxury_sedan | v8,automatic,premiumaudio,sunroof,navigation,towpackage | 12345          | 66200   |