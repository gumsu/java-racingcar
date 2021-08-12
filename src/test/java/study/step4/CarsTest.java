package study.step4;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import study.step4.model.car.Cars;
import study.step4.model.strategy.MoveStrategy;

public class CarsTest {

  @DisplayName("단일 자동차 이름 추가 테스트")
  @ParameterizedTest
  @ValueSource(strings = {"car1", "car2", "car3"})
  public void carNameAddTest(String carName) {
    Cars cars = Cars.of(carName);
    for (int i = 0; i < cars.getRacingGameCarsCount(); i++) {
      assertThat(cars.getCar(i).getName()).isEqualTo(carName);
    }
  }

  @DisplayName("Car Move Test")
  @ParameterizedTest
  @ValueSource(strings = {"car1", "car2", "car3"})
  void carMoveTest(String carNames) {
    Cars cars = Cars.of(carNames);
    MoveStrategy moveStrategy = () -> true;
    cars.move(moveStrategy);
    for (int i = 0; i < cars.getRacingGameCarsCount(); i++) {
      assertThat(cars.getCar(i).getDistance()).isEqualTo(1);
    }
  }

  @DisplayName("Car Move Stop Test")
  @ParameterizedTest
  @ValueSource(strings = {"car1", "car2", "car3"})
  void carStopTest(String carNames) {
    Cars cars = Cars.of(carNames);
    MoveStrategy moveStrategy = () -> false;
    cars.move(moveStrategy);
    for (int i = 0; i < cars.getRacingGameCarsCount(); i++) {
      assertThat(cars.getCar(i).getDistance()).isEqualTo(0);
    }
  }
}