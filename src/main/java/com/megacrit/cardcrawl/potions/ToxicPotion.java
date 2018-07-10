package com.megacrit.cardcrawl.potions;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.NecroticPoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.FrailPower;
import replayTheSpire.*;
import java.util.ArrayList;

public class ToxicPotion
  extends AbstractPotion
{
  public static final String POTION_ID = "Toxic Potion";
  private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("Toxic Potion");
  public static final String NAME = potionStrings.NAME;
  public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
  
  private int secondPotency = 2;
  
  public ToxicPotion()
  {
    super(NAME, "Toxic Potion", PotionRarity.UNCOMMON, AbstractPotion.PotionSize.JAR, AbstractPotion.PotionColor.POISON);
	this.secondPotency = 2;
	this.potency = this.getPotency();
    this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);// + DESCRIPTIONS[1] + (this.secondPotency - 1) + DESCRIPTIONS[2] + this.secondPotency + DESCRIPTIONS[3]
    this.isThrown = true;
    this.tips.add(new PowerTip(this.name, this.description));
    this.tips.add(new PowerTip(
      TipHelper.capitalize("necrotic poison"), 
      (String)GameDictionary.keywords.get("necrotic poison")));
  }
  
  public void use(AbstractCreature target)
  {
    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
      if (!m.isDeadOrEscaped()) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player, new NecroticPoisonPower(m, AbstractDungeon.player, this.potency), this.potency));
      }
    }
	
	//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VulnerablePower(AbstractDungeon.player, this.secondPotency - 1, false), this.secondPotency - 1));
	//AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new FrailPower(AbstractDungeon.player, this.secondPotency, false), this.secondPotency));
  }
  @Override
    public int getPotency(final int ascensionLevel) {
        return 5;//(ascensionLevel < 11) ? 5 : 4;
    }
  public AbstractPotion makeCopy()
  {
    return new ToxicPotion();
  }
  
  
}